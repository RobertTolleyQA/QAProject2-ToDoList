'use strict'

// CREATE SELECTORS
const deptname = document.querySelector("#createname");
const createalert = document.querySelector("#createonsuccess");
// READ SELECTORS
const listAll = document.querySelector("#deptInsert");
const readid = document.querySelector("#readoneid");
// UPDATE SELECTORS
const updatedeptid = document.querySelector("#updateid");
const updatedeptname = document.querySelector("#updatename");
const updatealert = document.querySelector("#updateonsuccess");
// DELETE SELECTORS
const deletedeptid = document.querySelector("#deleteid");
const deletealert = document.querySelector("#deleteonsuccess");

// CAPITALIZE


function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
}


// TASK TO STRING


const taskConverter = (tasks) => {
    let id = tasks.id;
    let name = capitalizeFirstLetter(tasks.name);
    let desc = tasks.desc;
    let cost = tasks.estCost;
    let workers = tasks.estWorkers;

    let finalString = `Tasks Title: ${name} Estimated Cost: £${cost} Estimated Workers: ${workers} Description: ${desc}`
    printNameToScreen(finalString, 1);
}


// JSON TO STRING


const jsonConverter = (dept) => {
    let id = dept.id;
    let name = capitalizeFirstLetter(dept.name);
    let list = dept.taskList;

    let finalString = `ID: ${id} - ${name}`;
    printNameToScreen(finalString, 0);

    for (let tasks of list) {
        taskConverter(tasks);

    }
}


// PRINT HEADER + BODY


const printNameToScreen = (tasks, heading) => {
    if (heading == 0) {
        let task = document.createElement("h4"); // <p> </p>
        let text = document.createTextNode(`${tasks}`); // username
        task.appendChild(text); // <p> username </p>
        listAll.appendChild(task);
    }
    else {
        let task = document.createElement("div"); // <p> </p>
        let text = document.createTextNode(`${tasks}`); // username
        task.appendChild(text); // <p> username </p>
        listAll.appendChild(task);
    }
}


    // CREATE METHOD


const createDept = () => {
    const deptName = deptname.value;
    const alert = createalert;

    if (deptName == "") {
        console.log("needs input");
        alert.setAttribute("class", "alert alert-danger");
        alert.innerHTML = "Department has not been successfully created!";
        setTimeout(() => {
            alert.removeAttribute("class");
            alert.innerHTML = "";
        }, 2000);
    }
    else {
        let data = {
            name: deptName
        }
        console.log(data);
        fetch("http://localhost:8080/dept/create", {
            method: "POST",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        })
            .then(response => response.json())
            .then(info => {
                console.log(info);
                alert.setAttribute("class", "alert alert-success");
                alert.innerHTML = "Department has been successfully created!";
                setTimeout(() => {
                    alert.removeAttribute("class");
                    alert.innerHTML = "";
                }, 2000);
            })
            .catch(err => console.error(`Stopppppp! ${err}`));
    }
}



    // READ ALL METHOD


const readDept = () => {
    document.getElementById("deptInsert").innerHTML = "";
    fetch("http://localhost:8080/dept/readAll")
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    for (let depts of infofromserver) {

                        jsonConverter(depts);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}



    // READ ONE METHOD


const readOneDept = () => {
    document.getElementById("deptInsert").innerHTML = "";

    const id = readid.value;

    fetch(`http://localhost:8080/dept/readOne/${id}`)
        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                //json-ify it (which returns a promise)
                response.json().then((infofromserver) => {
                    console.log(infofromserver);

                    jsonConverter(infofromserver);

                })
            }
        }).catch((err) => {
            console.error(err);
        })
}



    // CLEAR METHOD


const clearDept = () => {
    document.getElementById("deptInsert").innerHTML = "";
    console.log("Cleared");

}


    // UPDATE METHOD


const updateDept = () => {

    const deptID = updatedeptid.value;
    const deptName = updatedeptname.value;
    const alert = updatealert;

    if (deptID == "" || deptName == "") {
        console.log("needs input");
        alert.setAttribute("class", "alert alert-danger");
        alert.innerHTML = "Department has not been successfully updated!";
        setTimeout(() => {
            alert.removeAttribute("class");
            alert.innerHTML = "";
        }, 2000);
    }
    else {
        let data = {
            name: deptName
        }
        console.log(data);
        fetch(`http://localhost:8080/dept/update/${deptID}`, {
            method: "PUT",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        })
            .then(response => response.json())
            .then(info => {
                console.log(info);
                alert.setAttribute("class", "alert alert-success");
                alert.innerHTML = "Department has been successfully updated!";
                setTimeout(() => {
                    alert.removeAttribute("class");
                    alert.innerHTML = "";
                }, 2000);
            })
            .catch(err => console.error(`Stopppppp! ${err}`));


    }

}


    // DELETE METHOD


const deleteDept = () => {

    const deptID = deletedeptid.value;
    const alert = deletealert;

    fetch(`http://localhost:8080/dept/delete/${deptID}`, {

        method: "DELETE"
    })

        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 204) {
                throw new Error("I don't have a status of 204");
            } else {
                console.log(response);
                console.log(`Department has been deleted`);
            }
        }).then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-danger");
            alert.innerHTML = "Department has been successfully deleted!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })

        .catch((err) => {
            console.error(err);
        })
}

