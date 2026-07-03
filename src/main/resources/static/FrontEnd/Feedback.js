document.getElementById("feedbackForm")
.addEventListener("submit", function(event)
{
    event.preventDefault();

    const feedbackData =
    {
        customerName:
            document.getElementById("customerName").value,

        email:
            document.getElementById("email").value,

        rating:
            document.getElementById("rating").value,

        message:
            document.getElementById("message").value
    };

    fetch("/api/feedback",
    {
        method: "POST",

        headers:
        {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(feedbackData)
    })

    .then(response => response.text())

    .then(data =>
    {
        alert(data);

        document.getElementById("feedbackForm").reset();
    })

    .catch(error =>
    {
        alert("Error submitting feedback.");
        console.log(error);
    });
});