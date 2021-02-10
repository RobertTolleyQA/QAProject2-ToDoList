'use strict'

// CREATE SELECTORS
const deptname = document.querySelector("#createname");
// READ SELECTORS
const listAll = document.querySelector("#deptInsert")
// UPDATE SELECTORS
const updatetaskid = document.querySelector("#updateid");
const updatetaskname = document.querySelector("#updatename");
const updatetaskcost = document.querySelector("#updateprice");
const updatetaskworkers = document.querySelector("#updateworkers");
const updatetaskdesc = document.querySelector("#updatedesc")
// DELETE SELECTORS
const deletetaskid = document.querySelector("#deleteid");

// Task JSON to string

const taskConverter = (tasks) => {
    let id = tasks.id;
    let name = tasks.name;
    let desc = tasks.desc;
    let cost = tasks.estCost;
    let workers = tasks.estWorkers;

    let finalString = `Tasks Title: ${name} Estimated Cost: £${cost} Estimated Workers: ${workers} Description: ${desc}`
    printNameToScreen(finalString, 1);
}

const jsonConverter = (dept) => {
    let id = dept.id;
    let name = dept.name;
    let list = dept.taskList;

    let finalString = `ID: ${id} Title: ${name}`;
    printNameToScreen(finalString, 0);

    for (let tasks of list) {
        taskConverter(tasks);

}}

//  Clear previous read all

const clearRead = () => {
    document.getElementById("allTasks").innerHTML = "";

}

// Create <P> & add text

const printNameToScreen = (tasks, heading) => {
    if (heading == 0) {
    let task = document.createElement("h4"); // <p> </p>
    let text = document.createTextNode(`${tasks}`); // username
    task.appendChild(text); // <p> username </p>
    listAll.appendChild(task); }
    else {
        let task = document.createElement("div"); // <p> </p>
        let text = document.createTextNode(`${tasks}`); // username
        task.appendChild(text); // <p> username </p>
        listAll.appendChild(task); 
    }
}

//create method
const createDept = () => {
    const deptName = deptname.value;

    if (deptName == "") {
        console.log("needs input");
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
            })
            .catch(err => console.error(`Stopppppp! ${err}`));
    }
}

// Read all method

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

const updateTask = () => {

    const taskID = updatetaskid.value;
    const taskName = updatetaskname.value;
    const taskCost = updatetaskcost.value;
    const taskWorker = updatetaskworkers.value;
    const taskDesc = updatetaskdesc.value;

    if (taskID == "" || taskName == "" || taskCost == "" || taskWorker == "" || taskDesc == "") {
        console.log("needs input");
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
            })
            .catch(err => console.error(`Stopppppp! ${err}`));


    }

}

const deleteTask = () => {

        const taskID = deletetaskid.value;

        fetch(`http://localhost:8080/tasks/delete/${taskID}`, {
            
                method: "DELETE" })
        
            .then((response) => {
                // check that the response is OK (i.e. 200)
                if (response.status !== 204) {
                    throw new Error("I don't have a status of 204");
                } else {
                    console.log(response);
                    console.log(`Task has been deleted`);
                }
            }).catch((err) => {
                console.error(err);
            })
    }
    
