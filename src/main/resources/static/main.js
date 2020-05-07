let addRowBien = function () {
    let listNameB = 'biens'; //list name in Catalog.class
    let fieldsNamesB = ['titreBien', 'qte'];
    let rowIndexB = document.querySelectorAll('.item').length;

    let rowB = document.createElement('div');
    rowB.classList.add('row', 'item');


    fieldsNamesB.forEach((fieldName) => {
        let colB = document.createElement('div');
        colB.classList.add('col', 'form-group');

        let input = document.createElement('input');

        if (fieldName === 'qte') {
            input.type = 'number';
        } else {
            input.type = 'text';

        }
        input.classList.add('form-control');
        input.id = listNameB + rowIndexB + '.' + fieldName;
        input.setAttribute('name', listNameB + '[' + rowIndexB + '].' + fieldName);

        colB.appendChild(input);
        rowB.appendChild(colB);
    });

    document.getElementById('eventList').appendChild(rowB);

};

let addRowMission = function () {
    let listName = 'missions';
    let fieldsNames = ['titre', 'description'];
    let rowIndex = document.querySelectorAll('.item').length;

    let row = document.createElement('div');
    row.classList.add('row', 'item');

    fieldsNames.forEach((fieldName) => {
        let col = document.createElement('div');
        col.classList.add('col', 'form-group');


        let input = document.createElement('input');
        input.type = 'text';
        input.classList.add('form-control');
        input.id = listName + rowIndex + '.' + fieldName;
        input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);

        col.appendChild(input);
        row.appendChild(col);
    });

    document.getElementById('missionList').appendChild(row);
};


let deleteRow = function () {

};
