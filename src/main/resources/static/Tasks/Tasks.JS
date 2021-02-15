'use strict'

// CREATE SELECTORS
const listAll = document.querySelector("#taskInsert");
const taskid = document.querySelector("#id");
const taskname = document.querySelector("#name");
const taskcost = document.querySelector("#price");
const taskworkers = document.querySelector("#workers");
const taskdesc = document.querySelector("#desc");
const deptid = document.querySelector("#deptid");
const createalert = document.querySelector("#createonsuccess");
// READ SELECTORS
const readid = document.querySelector("#readoneid");
// UPDATE SELECTORS
const updatetaskid = document.querySelector("#updateid");
const updatetaskname = document.querySelector("#updatename");
const updatetaskcost = document.querySelector("#updateprice");
const updatetaskworkers = document.querySelector("#updateworkers");
const updatetaskdesc = document.querySelector("#updatedesc")
const updatealert = document.querySelector("#updateonsuccess");
// DELETE SELECTORS
const deletetaskid = document.querySelector("#deleteid");
const deletealert = document.querySelector("#deleteonsuccess");

// Task JSON to string

function capitalizeFirstLetter(string) {
    return string.charAt(0).toUpperCase() + string.slice(1);
  }

// Converts from json to a string

const jsonConverter = (tasks) => {
    let id = tasks.id;
    let name = capitalizeFirstLetter(tasks.name);
    let desc = tasks.desc;
    let cost = tasks.estCost;
    let workers = tasks.estWorkers;

    let finalHeader = `ID: ${id} Task : ${name}`;
    let finalString = `Estimated Cost: £${cost} Estimated Workers: ${workers} Description: ${desc}`;
    printNameToScreen(finalHeader, 0);
    printNameToScreen(finalString, 1);

}

//  Clear previous read all

const clearRead = () => {
    document.getElementById("allTasks").innerHTML = "";

}

// Create <P> & add text

const printNameToScreen = (tasks, size) => {
    if (size == 0) {
        let task = document.createElement(`h6`); // <p> </p>
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

//create method
const createTask = () => {
    const taskName = taskname.value;
    const taskCost = taskcost.value;
    const taskWorker = taskworkers.value;
    const taskDesc = taskdesc.value;
    const deptID = deptid.value;
    const alert = createalert;

    if (taskName == "" || taskCost == "" || taskWorker == "" || taskDesc == "" || deptID == "") {
        console.log("needs input");
        alert.setAttribute("class", "alert alert-danger");
        alert.innerHTML = "Task has not been successfully created!";
        setTimeout(() => {
            alert.removeAttribute("class");
            alert.innerHTML = "";
        }, 2000);
    }
    else {
        let data = {
            name: taskName,
            desc: taskDesc,
            estCost: taskCost,
            estWorkers: taskWorker,
            myDepartment: { "id": deptID }
        }
        console.log(data);
        fetch("http://localhost:8080/tasks/create", {
            method: "POST",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        })
            .then(response => response.json())
            .then(info => {
                console.log(info);
                alert.setAttribute("class", "alert alert-success");
                alert.innerHTML = "Task has been successfully created!";
                setTimeout(() => {
                    alert.removeAttribute("class");
                    alert.innerHTML = "";
                }, 2000);
            })
            .catch(err => console.error(`Stopppppp! ${err}`));
    }
}

// Read all method

const readTask = () => {
    document.getElementById("taskInsert").innerHTML = "";
    fetch("http://localhost:8080/tasks/readAll")
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
                    for (let tasks of infofromserver) {

                        jsonConverter(tasks);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}

// READ ONE

const readOneTask = () => {
    document.getElementById("taskInsert").innerHTML = "";

    const id = readid.value;

    fetch(`http://localhost:8080/tasks/readOne/${id}`)
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

// CLEAR READ 

const clearDept = () => {
    document.getElementById("taskInsert").innerHTML = "";
    console.log("Cleared");

}

// UPDATE


const updateTask = () => {

    const taskID = updatetaskid.value;
    const taskName = updatetaskname.value;
    const taskCost = updatetaskcost.value;
    const taskWorker = updatetaskworkers.value;
    const taskDesc = updatetaskdesc.value;
    const alert = updatealert;

    if (taskID == "" || taskName == "" || taskCost == "" || taskWorker == "" || taskDesc == "") {
        console.log("needs input");
        alert.setAttribute("class", "alert alert-danger");
        alert.innerHTML = "Task has not been successfully updated!";
        setTimeout(() => {
            alert.removeAttribute("class");
            alert.innerHTML = "";
        }, 2000);
    }
    else {
        let data = {
            name: taskName,
            desc: taskDesc,
            estCost: taskCost,
            estWorkers: taskWorker
        }
        console.log(data);
        fetch(`http://localhost:8080/tasks/update/${taskID}`, {
            method: "PUT",
            body: JSON.stringify(data),
            headers: { "Content-Type": "application/json" }
        })
            .then(response => response.json())
            .then(info => {
                console.log(info);
                alert.setAttribute("class", "alert alert-success");
                alert.innerHTML = "Task has been successfully updated!";
                setTimeout(() => {
                    alert.removeAttribute("class");
                    alert.innerHTML = "";
                }, 2000);
            })
            .catch(err => console.error(`Stopppppp! ${err}`));


    }

}

// DELETE

const deleteTask = () => {

    const taskID = deletetaskid.value;
    const alert = deletealert;

    fetch(`http://localhost:8080/tasks/delete/${taskID}`, {

        method: "DELETE"
    })

        .then((response) => {
            // check that the response is OK (i.e. 200)
            if (response.status !== 204) {
                throw new Error("I don't have a status of 204");
            } else {
                console.log(response);
                console.log(`Task has been deleted`);
            }
        }).then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-danger");
            alert.innerHTML = "Task has been successfully deleted!";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })

        .catch((err) => {
            console.error(err);
        })
}

