import React from 'react';
import './RaceForm.css';

class RaceForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {name: '', requiredEngineCapacity: ''};

    }

    // handleIdChange = (event) => {
    //     this.setState({id: event.inputId.current.value()});
    // }

    // handleNameChange = (event) => {
    //     this.setState({name: this.props.inputName.current.value});
    // }
    //
    // handleRequiredEngineCapacityChange = (event) => {
    //     this.setState({requiredEngineCapacity: this.props.inputEngine.current.value});
    // }

    handleResetInputs() {
        this.props.inputId.current.value = "";
        this.props.inputName.current.value = "";
        this.props.inputEngine.current.value = "";
    }

    handleSubmit = (event) => {
        let race = {
            name: this.props.inputName.current.value,
            requiredEngineCapacity: this.props.inputEngine.current.value
        }

        if (race.name === "" || race.requiredEngineCapacity === "") {
            this.handleResetInputs();
            return ;
        }

        console.log('A race was submitted: ');
        console.log(race);

        this.props.addFunc(race);
        this.handleResetInputs();
        event.preventDefault();
    }

    handleUpdate = (event) => {
        event.preventDefault();

        if (this.props.inputId.current.value === "") {
            this.handleResetInputs();
            return;
        }

        let race = {
            id: this.props.inputId.current.value,
            name: this.props.inputName.current.value,
            requiredEngineCapacity: this.props.inputEngine.current.value
        }

        console.log('A race was updated: ');
        console.log(race);

        this.props.updateFunc(race);
        this.handleResetInputs();
    }

    render() {
        return (
            <div>
                <form>
                    <label>
                        <div>Id:</div>
                        <input ref={this.props.inputId} disabled={true} type="text"/>
                    </label>
                    <label>
                        <div>Name:</div>
                        <input ref={this.props.inputName} required={true} type="text" onChange={this.handleNameChange}/>
                    </label><br/>
                    <label>
                        <div>RequiredEngineCapacity:</div>
                        <select ref={this.props.inputEngine} required={true}
                                onChange={this.handleRequiredEngineCapacityChange}>
                            <option/>
                            <option>EngineCapacity125Cc</option>
                            <option>EngineCapacity250Cc</option>
                            <option>EngineCapacity500Cc</option>
                        </select>
                    </label><br/>
                    <div>
                        <button id={"buttonUpdate"} onClick={this.handleUpdate}>Update</button>
                    </div>
                    <div>
                        <button id={"buttonUpdate"} onClick={this.handleSubmit}>Submit</button>
                    </div>
                </form>
            </div>
        );
    }
}

export default RaceForm;