'use strict'

//const flower = document.querySelector("#flower");
const taskname = document.querySelector("#name");
const taskcost = document.querySelector("#price");
const taskworkers = document.querySelector("#workers");
const taskdesc = document.querySelector("#desc");
// const flowerid = document.querySelector("#id");

//create flower method
const createTask = () => {
    const taskName = taskname.value;
    const taskCost = taskcost.value;
    const taskWorker = taskworkers.value;
    const taskDesc = taskdesc.value;

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