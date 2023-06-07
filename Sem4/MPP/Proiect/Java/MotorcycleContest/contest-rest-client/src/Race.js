import React from 'react';
import './RaceApp.css'
import './Race.css'

class RaceRow extends React.Component {

    handleClick = (event) => {
        console.log('delete button for ' + this.props.race.name);
        this.props.deleteFunc(this.props.race.name);
    }

    handleLoadForm = (event) => {
        let race = {
            id: this.props.race.id,
            name: this.props.race.name,
            requiredEngineCapacity: this.props.race.requiredEngineCapacity
        }

        console.log('A race was selected ', race);

        this.props.handleLoad(race);
    }


    render() {
        return (
            <tr>
                <td onClick={this.handleLoadForm}>{this.props.race.id}</td>
                <td onClick={this.handleLoadForm}>{this.props.race.name}</td>
                <td onClick={this.handleLoadForm}>{this.props.race.requiredEngineCapacity}</td>
                <td>
                    <button onClick={this.handleClick}>Delete</button>
                </td>
            </tr>
        );
    }
}

class RaceTable extends React.Component {
    render() {
        let rows = [];
        let deleteFunction = this.props.deleteFunc;
        let handleLoadFunction = this.props.handleLoad;

        for (let i = 0, len = this.props.races.length; i < len; i++) {
            rows.push(<RaceRow race={this.props.races[i]}
                               key={this.props.races[i].id}
                               deleteFunc={deleteFunction}
                               handleLoad={handleLoadFunction}/>
                               );
        }

        return (<div className="RaceTable">

                <table className="center">
                    <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>RequiredEngineCapaciy</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                </table>

            </div>
        );
    }
}

export default RaceTable;