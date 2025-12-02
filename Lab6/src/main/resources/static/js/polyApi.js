const host = "http://localhost:8080";
const $api = {
    url0(){
        var url = `${host}/poly/url0`;
        axios.get(url).then(resp => {
            console.log("Success", resp.data);
            displayDataOnFrontend(resp.data);
        }).catch(error => {
            console.log("Error", error);
        })
    },
    url1(){
        var url = `${host}/poly/url1`;
        axios.get(url).then(resp => {
            console.log("Success", resp.data);
            displayDataOnFrontend(resp.data);
        }).catch(error => {
            console.log("Error", error);
        })
    },
    url2(){
        var url = `${host}/poly/url2`;
        axios.get(url).then(resp => {
            console.log("Success", resp.data);
            displayDataOnFrontend(resp.data);
        }).catch(error => {
            console.log("Error", error);
        })
    },
    url3(){
        var url = `${host}/poly/url3`;
        axios.get(url).then(resp => {
            console.log("Success", resp.data);
            displayDataOnFrontend(resp.data);
        }).catch(error => {
            console.log("Error", error);
        })
    },
    url4(){
        var url = `${host}/poly/url4`;
        axios.get(url).then(resp => {
            console.log("Success", resp.data);
            displayDataOnFrontend(resp.data);
        }).catch(error => {
            console.log("Error", error);
        })
    },
}

function displayDataOnFrontend(data) {
    const outputElement = document.getElementById("api-output");

    const url = data.url;
    const method = data.method;

    outputElement.innerHTML = `
        <p><strong>URL đã gọi:</strong> ${url}</p>
        <p><strong>Phương thức Controller:</strong> ${method}</p>
    `;
}