(function ($) {
    $("#submitButton").click(function(event) {
        // Stop default form Submit.
        event.preventDefault();

        var form = $("#fileUploadForm")[0];
        var data = new FormData(form);

        $("#submitButton").prop("disabled", true);

        $.ajax({
            type: "POST",
            enctype: 'multipart/form-data',
            url: "/admin/uploadSlider",
            data: data,

            // prevent jQuery from automatically transforming the data into a query string
            processData: false,
            contentType: false,
            cache: false,
            timeout: 1000000
            , success: function(data, textStatus, jqXHR) {
                $("#result").html(data);
                console.log("SUCCESS : ", data);
                $("#submitButton").prop("disabled", false);
                $("#fileUploadForm")[0].reset();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                $("#result").html(jqXHR.responseText);
                console.log("ERROR : ", jqXHR.responseText);
                $("#submitButton").prop("disabled", false);

            }
        });
    });
})(jQuery);
