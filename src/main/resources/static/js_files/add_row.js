let addRowBien = function () {
    let listNameB = 'biens'; //list name in Catalog.class
    let fieldsNamesB = ['titreBien', 'qte', 'button'];
    let rowIndexB = document.querySelectorAll('.b').length;
    console.log('rowIndexB', rowIndexB);
    let rowB = document.createElement('div');
    rowB.classList.add('row', 'b');
    rowB.setAttribute('id', 'r' + rowIndexB);
    console.log('rowB.id', rowB.id);

    fieldsNamesB.forEach((fieldName) => {
        let colB = document.createElement('div');
        colB.classList.add('col', 'form-group');
        let input = document.createElement('input');

        if (fieldName === 'qte') {
            input.type = 'number';
            input.id = listNameB + rowIndexB + '.' + fieldName;
            console.log('  input.id', input.id);
            input.setAttribute('id', input.id);
            input.setAttribute('required', 'required');

            input.setAttribute('name', listNameB + '[' + rowIndexB + '].' + fieldName);
            input.classList.add('form-control');


        } else if (fieldName === 'button') {
            input.type = 'button';
            input.id = rowIndexB;

            input.setAttribute('type', 'button');
            input.setAttribute('value', 'Effacer');

            // add button's "onclick" event.
            input.setAttribute('onclick', 'removeRowBien(this)');
            input.classList.add('btn', 'btn-danger');


        } else {
            input.type = 'text';
            input.id = listNameB + rowIndexB + '.' + fieldName;
            input.setAttribute('id', input.id);

            console.log('  input.id', input.id);
            input.setAttribute('required', 'required');

            input.setAttribute('name', listNameB + '[' + rowIndexB + '].' + fieldName);
            input.classList.add('form-control');

        }


        colB.appendChild(input);

        rowB.appendChild(colB);
    });

    document.getElementById('eventList').appendChild(rowB);


};

function removeRowBien(r) {
    r = document.getElementById('r' + r.id);
    r.remove();


}

let addRowMission = function () {
    let listName = 'missions';
    let fieldsNames = ['titre', 'description', 'button'];
    let rowIndex = document.querySelectorAll('.m').length;

    let row = document.createElement('div');
    row.classList.add('row', 'm');
    row.setAttribute('id', 'idm' + rowIndex);

    fieldsNames.forEach((fieldName) => {
        let col = document.createElement('div');
        col.classList.add('col', 'form-group');


        let input = document.createElement('input');

        if (fieldName === 'button') {
            input.type = 'button';
            input.id = 'm' + rowIndex;
            console.log(input.id);

            input.setAttribute('type', 'button');
            input.setAttribute('value', 'Effacer');

            // add button's "onclick" event.
            input.setAttribute('onclick', 'removeRowMission(this)');
            input.classList.add('btn', 'btn-danger');


        } else {
            input.type = 'text';
            input.classList.add('form-control');
            input.id = listName + rowIndex + '.' + fieldName;
            input.setAttribute('name', listName + '[' + rowIndex + '].' + fieldName);
            input.setAttribute('required', 'required');

            input.classList.add('form-control');

        }


        col.appendChild(input);
        row.appendChild(col);
    });

    document.getElementById('missionList').appendChild(row);
};

function removeRowMission(r) {
    r = document.getElementById('id' + r.id);
    r.remove();


}


/*
let removeRow = function (oButton) {
    console.log("oButton", oButton)

   // var empTab = document.getElementById('empTable');
    //empTab.remove();
    $( "div" ).remove( ".item" );

}
*/
