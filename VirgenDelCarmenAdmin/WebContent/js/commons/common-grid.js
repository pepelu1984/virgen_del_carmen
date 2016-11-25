
getColumnIndexByName = function(grid, columnName) {
    var cm = grid.jqGrid('getGridParam','colModel'),i=0,l=cm.length;
    for (; i<l; i++) {
        if (cm[i].name===columnName) {
            return i; // return the index
        }
    }
    return -1;
};
loadComplete = function() {
    $("tr.jqgrow:odd").addClass('myAltRowClass');
}
