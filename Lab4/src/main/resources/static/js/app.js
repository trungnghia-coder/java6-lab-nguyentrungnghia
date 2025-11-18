const host = "https://java6-37c94-default-rtdb.firebaseio.com";

function doGetAll() {
    var url = `${host}/student.json`;
    axios.get(url).then(resp => {
        console.log("Success", resp.data);
    }).catch(error => {
        console.log("Error", error);
    });
}

function doGetByKey() {
    var key = "-OdlAFZoKO03lz_zR9UA";
    var url = `${host}/student/${key}.json`;
    axios.get(url).then(resp => {
        console.log("Success", resp.data);
    }).catch(error => {
        console.log("Error", error);
    })
}

function doPost() {
    var url = `${host}/student.json`;
    var data = {
        "id": "SV99",
        "name": "Sinh viên 99",
        "mark": 4.5,
        "gender": true
    }
    axios.post(url, data).then(resp => {
        console.log("Success", resp.data);
    }).catch(error => {
        console.log("Error", error);
    })
}

function doPut() {
    var key = "<<key>>";
    var url = `${host}/student/${key}.json`;
    var data = {
        "id": "SV00",
        "name": "Sinh viên 00",
        "mark": 8.5,
        "gender": true
    }
    axios.put(url, data).then(resp => {
        console.log("Success", resp.data);
    }).catch(error => {
        console.log("Error", error);
    })
}

function doDelete() {
    var key = "<<key>>";
    var url = `${host}/student/${key}.json`;
    axios.delete(url).then(resp => {
        console.log("Success", resp.data);
    }).catch(error => {
        console.log("Error", error);
    })
}