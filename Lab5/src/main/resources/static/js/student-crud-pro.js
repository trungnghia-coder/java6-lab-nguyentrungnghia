const host = "http://localhost:8080";
const $api = {
    currentId: null,
    get student(){
        return {
            id: $("#id").val(),
            name: $("#name").val(),
            mark: $("#mark").val(),
            gender: $("#male").prop("checked")
        }
    },
    set student(e){
        $("#id").val(e.id);
        $("#name").val(e.name);
        $("#mark").val(e.mark);
        $("#male").prop("checked", e.gender);
        $("#female").prop("checked", !e.gender);
        this.currentId = e.id;
    },
    fillToTable() {
        var url = `${host}/students`;
        axios.get(url).then(resp => {
            $("tbody").empty();
            resp.data.forEach(e => {
                var tr =
                    `<tr> 
                        <td>${e.id}</td>
                        <td>${e.name}</td>
                        <td>${e.mark}</td>
                        <td>${e.gender?'Male':'Female'}</td>
                        <td>
                        <a href="#" onclick="$api.edit('${e.id}')">Edit</a>
                        <a href="#" onclick="$api.delete('${e.id}')">Delete</a>
                        </td>
                    </tr>`;
                $("tbody").append(tr);
            });
        }).catch(error => {
            alert("Lỗi tải danh sách sinh viên!");
        })
    },
    edit(id) {
        var url = `${host}/students/${id}`;
        axios.get(url).then(resp => {
            this.student = resp.data;
        }).catch(error => {
            alert("Lỗi tải sinh viên!");
        })
    },
    create() {
        var url = `${host}/students`;
        axios.post(url, this.student).then(resp => {
            this.fillToTable();
            this.reset();
        }).catch(error => {
            alert("Lỗi thêm sinh viên mới!");
        })
    },
    update() {
        var idToUpdate = $("#id").val();
        var url = `${host}/students/${idToUpdate}`;
        axios.put(url, this.student).then(resp => {
            this.fillToTable();
        }).catch(error => {
            alert("Lỗi tải cập nhật sinh viên!");
        })
    },
    delete(id) {
        var idToDelete = id || this.currentId || $("#id").val();
        var url = `${host}/students/${idToDelete}`;
        axios.delete(url).then(resp => {
            this.fillToTable();
            this.reset();
        }).catch(error => {
            alert("Lỗi xóa sinh viên!");
        })
    },
    reset(){
        this.student = {};
        this.currentId = null;
        $("#male").prop("checked", true);
    }
}
$api.fillToTable();