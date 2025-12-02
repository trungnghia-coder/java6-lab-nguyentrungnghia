const host = "http://localhost:8080";
let token;

function login() {
    var url = "http://localhost:8080/poly/login";
    var data = {
        "username": "user@gmail.com",
        "password": "123"
    }
    axios.post(url, data).then(resp => {
        console.log("post() success", resp.data);
        token = resp.data.token;
        displayLoginResult(resp.data);
    }).catch(error => {
        console.log("post() error", error);
    })
}

function gotoUrl0() {
    var url = `http://localhost:8080/poly/url0`;
    axios.get(url).then(resp => {
        console.log("get() success", resp.data);
        displayDataOnFrontend(resp.data);
    }).catch(error => {
        console.log("get() error", error);
    })
}

function gotoUrl1() {
    var url = `http://localhost:8080/poly/url1`;
    var config = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    axios.get(url, config).then(resp => {
        console.log("get() success", resp.data);
        displayDataOnFrontend(resp.data);
    }).catch(error => {
        console.log("get() error", error);
    })
}

function gotoUrl2() {
    var url = `http://localhost:8080/poly/url2`;
    var config = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    axios.get(url, config).then(resp => {
        console.log("get() success", resp.data);
        displayDataOnFrontend(resp.data);
    }).catch(error => {
        console.log("get() error", error);
    })
}


function gotoUrl3() {
    var url = `http://localhost:8080/poly/url3`;
    var config = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    axios.get(url, config).then(resp => {

        console.log("get() success", resp.data);
        displayDataOnFrontend(resp.data);
    }).catch(error => {
        const statusCode = error.response.status;
        if (statusCode === 403) {
            const errorMessage = "Bạn không có đủ quyền (Role) để truy cập tài nguyên này!";
            alert(errorMessage);
        }
        console.log("get() error", error);
    })
}

function gotoUrl4() {
    var url = `http://localhost:8080/poly/url4`;
    var config = {
        headers: {
            "Authorization": `Bearer ${token}`
        }
    };
    axios.get(url, config).then(resp => {
        console.log("get() success", resp.data);
        displayDataOnFrontend(resp.data);
    }).catch(error => {
        console.log("get() error", error);
    })
}

function displayLoginResult(data) {
    const outputElement = document.getElementById("login-output");

    const username = data.username

    outputElement.innerHTML = `
        <p><strong>Username:</strong> ${username}</p>
    `;
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