import {CONTEST_RACES_BASE_UR} from './consts';

function status(response) {
    console.log('response status ' + response.status);

    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response);
    } else {
        return Promise.reject(new Error(response.statusText));
    }
}

function json(resonse) {
    return resonse.json();
}

export function GetRaces() {
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    let myInit = {
        mode: 'cors',
        method: 'GET',
        headers: headers,
    };
    let request = new Request(CONTEST_RACES_BASE_UR, myInit);

    console.log('Before fetch for ' + CONTEST_RACES_BASE_UR);

    return fetch(request)
        .then(status)
        .then(json)
        .then(data => {
            console.log('Request succeded with JSON response', data);
            return data;
        })
        .catch(error => {
            console.log('Request failed', error);
            return error;
        })
}


export function DeleteRace(name) {
    console.log('before fetch delete')
    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");

    let header = {
        mode: 'cors',
        method: 'DELETE',
        headers: myHeaders
    };

    let raceDeleteUrl = CONTEST_RACES_BASE_UR + '/' + name;

    return fetch(raceDeleteUrl, header)
        .then(status)
        .then(response => {
            console.log('Delete status ' + response.status);
            return response.text();
        }).catch(e => {
            console.log('error ' + e);
            return Promise.reject(e);
        });

}

export function AddRace(race) {
    console.log('before fetch post ' + JSON.stringify(race));

    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    myHeaders.append("Content-Type", "application/json");

    let header = {
        method: 'POST',
        headers: myHeaders,
        mode: 'cors',
        body: JSON.stringify(race)
    };

    return fetch(CONTEST_RACES_BASE_UR, header)
        .then(status)
        .then(response => {
            return response.text();
        }).catch(error => {
            console.log('Request failed', error);
            return Promise.reject(error);
        });
}

export function UpdateRace(race) {
    console.log('before fetch post ' + JSON.stringify(race));

    let newRace = {
        name: race.name,
        requiredEngineCapacity: race.requiredEngineCapacity
    }

    let myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");
    myHeaders.append("Content-Type","application/json");

    let header = {
        method: 'PUT',
        headers: myHeaders,
        mode: 'cors',
        body:JSON.stringify(newRace)
    };

    let url = CONTEST_RACES_BASE_UR + '/' + race.id;

    return fetch(url, header)
        .then(status)
        .then(response => {
            return response.text();
        }).catch(error => {
            console.log('Request failed ', error);
            return Promise.reject(error);
        })
}