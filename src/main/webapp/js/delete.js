function deleteComplaint(complaintID) {
    console.log(complaintID)
    $.ajax({
        url: "/master",
        type: "POST",
        data: {
            ac: "deleteComplaint",
            complaintID: complaintID
        },
        success: function (response) {
            location.reload();
        },
        error: function (xhr, status, error) {
            console.error(error);
        }
    });
}

