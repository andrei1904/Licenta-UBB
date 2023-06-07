import React from 'react';
import RaceTable from './Race';
import './RaceApp.css';
import RaceForm from "./RaceForm";
import {GetRaces, AddRace, DeleteRace, UpdateRace} from './utils/rest-calls';


class RaceApp extends React.Component {
    constructor(props) {
        super(props);
        this.inputId = React.createRef();
        this.inputName = React.createRef();
        this.inputEngine = React.createRef();

        this.state = {
            races: [{"id": "0", "name": "Marinescu Maria", "requiredEngineCapacity": "EngineCapacity250Cc"}],
            deleteFunc: this.deleteFunc.bind(this),
            addFunc: this.addFunc.bind(this),
            handleLoad: this.handleLoad.bind(this),
            updateFunc: this.updateFunc.bind(this),
            inputId: this.inputId,
            inputName: this.inputName,
            inputEngine: this.inputEngine,
        }
        console.log('RaceApp constructor')
    }

    handleLoad(data) {
        this.inputId.current.value = data.id;
        this.inputName.current.value = data.name;
        this.inputEngine.current.value = data.requiredEngineCapacity;
    }

    addFunc(race) {
        console.log('Inside add func ' + race);
        AddRace(race)
            .then(res => GetRaces())
            .then(races => this.setState({races}))
            .catch(erorr => console.log('error add ', erorr));
    }

    updateFunc(race) {
        console.log('Inside update func ' + race);

        UpdateRace(race)
            .then(res => GetRaces())
            .then(races => this.setState({races}))
            .catch(error => console.log('error update ' + error))
    }

    deleteFunc(name) {
        console.log('inside deleteFunc ' + name);
        DeleteRace(name)
            .then(res => GetRaces())
            .then(races => this.setState({races}))
            .catch(error => console.log('eroare delete', error));
    }


    componentDidMount() {
        console.log('inside componentDidMount')
        GetRaces().then(races => this.setState({races}));
    }

    render() {
        return (
            <div className="RaceApp">
                <h1>Motorcycle Contest</h1>
                <h3>Add new race</h3>
                <RaceForm inputId={this.state.inputId}
                          inputName={this.state.inputName}
                          inputEngine={this.state.inputEngine}
                          addFunc={this.state.addFunc}
                          updateFunc={this.state.updateFunc}/>
                <br/>
                <br/>
                <h3>All Races</h3>
                <RaceTable races={this.state.races} deleteFunc={this.state.deleteFunc}
                            handleLoad={this.state.handleLoad}/>
            </div>
        );
    }
}

export default RaceApp;