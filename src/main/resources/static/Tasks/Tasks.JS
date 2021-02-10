'use strict'

const listAll = document.querySelector("#allTasks");
const taskname = document.querySelector("#name");
const taskcost = document.querySelector("#price");
const taskworkers = document.querySelector("#workers");
const taskdesc = document.querySelector("#desc");
// const flowerid = document.querySelector("#id");

// Task JSON to string

const jsonConverter = (tasks) => {
    let id = tasks.id;
    let name = tasks.name;
    let desc = tasks.desc;
    let cost = tasks.estCost;
    let workers = tasks.estWorkers;

    let finalString = `ID: ${id} Tasks Title: ${name} Estimated Cost: £${cost} Estimated Workers: ${workers} Description: ${desc}`
    printNameToScreen(finalString);

}

//  Clear previous read all

const clearRead = () => {
    document.getElementById("allTasks").innerHTML = "";

}

// Create <P> & add text

const printNameToScreen = (tasks) => {
    let task = document.createElement("div"); // <p> </p>
    let text = document.createTextNode(`${tasks}`); // username
    task.appendChild(text); // <p> username </p>
    listAll.appendChild(task);
}

//create method
const createTask = () => {
    const taskName = taskname.value;
    const taskCost = taskcost.value;
    const taskWorker = taskworkers.value;
    const taskDesc = taskdesc.value;

    if (taskName == "" || taskCost == "" || taskWorker == "" || taskDesc == "") {
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
        fetch("http://localhost:8080/tasks/create", {
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

const readTask = () => {
    document.getElementById("allTasks").innerHTML = "";
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
                        console.log(tasks.name);
                        jsonConverter(tasks);
                    }
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}
