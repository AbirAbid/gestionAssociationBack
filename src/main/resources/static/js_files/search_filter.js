/********recherche memebres******/
$(document).ready(function () {


    $('#tableau tfoot th').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder=" ' + title + '"  size="6"  class="form-control"/>');
    });

    // DataTables est un plug-in jQuery open-source permettant de dynamiser un tableau HTML
    var otable = $('#tableau').DataTable({
        "pagingType": "simple_numbers",
        "language": {
            "paginate": {
                "next": "Suivant",
                "previous": "Précédent"
            },
            "search": "<strong> Rechercher </strong>",
        },
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false,

        "fnInitComplete": function (oSettings) {
            oSettings.oLanguage.sZeroRecords = "<center><span class=\"alert alert-secondary\" >Désolé, votre recherche n'a retourné aucun résultat.</span> </center>"
        },
        "columnDefs": [
            {"orderable": false, "targets": 5},
            {"orderable": false, "targets": 6},
            {"orderable": false, "targets": 7}


        ]
    });

    // Apply the search
    otable.columns().every(function () {

        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });


});


$(document).ready(function () {

    $('#example').DataTable({
        "pagingType": "simple_numbers",
        "language": {
            "paginate": {
                "next": "Suivant",
                "previous": "Précédent"
            },
            "search": "<strong> Rechercher </strong>",
        },
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false,

        "fnInitComplete": function (oSettings) {
            oSettings.oLanguage.sZeroRecords = "<center><span class=\"alert alert-warning\" >Désolé, votre recherche n'a retourné aucun résultat.</span> </center>"
        }
    });

});

/********recherche events******/

$(document).ready(function () {


    $('#listevent tfoot th').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder=" ' + title + '"  size="6"  class="form-control"/>');
    });

    // DataTables est un plug-in jQuery open-source permettant de dynamiser un tableau HTML
    var otable = $('#listevent').DataTable({
        "pagingType": "simple_numbers",
        "language": {
            "paginate": {
                "next": "Suivant",
                "previous": "Précédent"
            },
            "search": "<strong> Rechercher </strong>",
        },
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false,

        "fnInitComplete": function (oSettings) {
            oSettings.oLanguage.sZeroRecords = "<center><span class=\"alert alert-secondary\" >Désolé, votre recherche n'a retourné aucun résultat.</span> </center>"
        },
        "columnDefs": [
            {"orderable": false, "targets": 4},
            {"orderable": false, "targets": 5},
            {"orderable": false, "targets": 6}


        ]
    });

    // Apply the search
    otable.columns().every(function () {

        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });


});
/********recherche sponsors******/
$(document).ready(function () {


    $('#tableSponsor tfoot th').each(function () {
        var title = $(this).text();
        $(this).html('<input type="text" placeholder=" ' + title + '"  size="6"  class="form-control"/>');
    });

    // DataTables est un plug-in jQuery open-source permettant de dynamiser un tableau HTML
    var otable = $('#tableSponsor').DataTable({
        "pagingType": "simple_numbers",
        "language": {
            "paginate": {
                "next": "Suivant",
                "previous": "Précédent"
            },
            "search": "<strong> Rechercher </strong>",
        },
        "bLengthChange": false,
        "bFilter": true,
        "bInfo": false,
        "bAutoWidth": false,

        "fnInitComplete": function (oSettings) {
            oSettings.oLanguage.sZeroRecords = "<center><span class=\"alert alert-secondary\" >Désolé, votre recherche n'a retourné aucun résultat.</span> </center>"
        },
        "columnDefs": [
            {"orderable": false, "targets": 3},
            {"orderable": false, "targets": 4}


        ]
    });

    // Apply the search
    otable.columns().every(function () {

        var that = this;
        $('input', this.footer()).on('keyup change', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });


});
