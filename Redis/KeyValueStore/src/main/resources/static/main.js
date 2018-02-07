/**
 * Create HTML table row.
 *
 * \param text (str) HTML code to be placed inside the row.
 */
function tr(text) {
    return '<tr>' + text + '</tr>';
}

/**
 * Create HTML table cell element.
 *
 * \param text (str) The text to be placed inside the cell.
 */
function td(text) {
    return '<td>' + text + '</td>';
}

/**
 * Edit value: load key and value into inputs.
 */
function editButton(key) {
    var format = '<button '
        + 'class="btn btn-default" '
        + 'onclick="editKey(\'{key}\')" '
        + '>Edit</button>';
    return format.replace(/{key}/g, key);
}

/**
 * Create HTML button with which a key-value pair is deleted from persistence.
 *
 * \param key (str) that'll be deleted when the created button is clicked.
 */
function deleteButton(key) {
    var format = '<button '
        + 'class="btn btn-default" '
        + 'data-key="{key}" '
        + 'onclick="deleteKey(\'{key}\')" '
        + '>Delete</button>'
    return format.replace(/{key}/g, key);
}

/**
 * Create HTML table row element.
 *
 * \param key (str) text into the key cell.
 * \param value (str) text into the value cell.
 */
function row(key, name, age) {
    return $(
        tr(
            td(key) +
            td(name) +
            td(age) +
            td(editButton(key)) +
            td(deleteButton(key))));
}

/**
 * Clear and reload the values in data table.
 */
function refreshTable() {
//alert("refreshTable0");
    $.get('/values', function(data) {
        var attr,
            mainTable = $('#mainTable tbody');
        mainTable.empty();
        // alert("refreshTable1");

        for (attr in data) {
       // alert("attr"+attr);
            if (data.hasOwnProperty(attr)) {
                alert(data[attr].name);
                mainTable.append(row(attr, data[attr].name, data[attr].age));
            }
        }
    });
}

function editKey(key) {
    /* Find the row with key in first column (key column). */
    var format = '#mainTable tbody td:first-child:contains("{key}")',
        selector = format.replace(/{key}/, key),
        cells = $(selector).parent().children(),
        key = cells[0].textContent,
        name = cells[1].textContent,
        age = cells[2].textContent,
        keyInput = $('#keyInput'),
        nameInput = $('#nameInput'),
        ageInput = $('#ageInput');

    /* Load the key and value texts into inputs
     * Select value text so it can be directly typed to
     */
    keyInput.val(key);
    nameInput.val(name);
    ageInput.val(age);
    nameInput.select();
}

/**
 * Delete key-value pair.
 *
 * \param key (str) The key to be deleted.
 */
function deleteKey(key) {
    /*
     * Delete the key.
     * Reload keys and values in table to reflect the deleted ones.
     * Set keyboard focus to key input: ready to start typing.
     */
    $.post('/delete', {key: key}, function() {
        refreshTable();
        $('#keyInput').focus();
    });
}

$(document).ready(function() {
alert("ready");
    var keyInput = $('#keyInput'),
        nameInput = $('#nameInput'),
        ageInput = $('#ageInput');

    refreshTable();
    $('#addForm').on('submit', function(event) {
    //alert("submit"+keyInput.val());
        var data = {
            key: keyInput.val(),
            name: nameInput.val(),
            age: ageInput.val()
        };

        /*
         * Persist the new key-value pair.
         * Clear the inputs.
         * Set keyboard focus to key input: ready to start typing.
         */
        $.post('/add', data, function() {
        //alert("add"+data.key);
            refreshTable();
            keyInput.val('');
            nameInput.val('');
            ageInput.val('');
            keyInput.focus();
        });
        /* Prevent HTTP form submit */
        event.preventDefault();
    });

    /* Focus keyboard input into key input; ready to start typing */
    keyInput.focus();
});