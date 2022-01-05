/* Scoreboard JavaScript - Client update mechanism */

let globalClientRevision = 0;
function checkForGlobalUpdate() {
    $.get("/api/tool/getServerRevision", function(data, status) {
        if(status === "success") {
            let globalServerRevision = data * 1;
            if(globalServerRevision > globalClientRevision) {
                console.trace("checkForGlobalUpdate::New version. Let's reload.");
                location.reload();
            }
            else {
                console.trace("Nothing to update. :: GlobalClientRevision: " + globalClientRevision);
            }
        }
        else {
            console.log("Update check failed.");
        }
    });
}

function startAutoUpdate(revision) {
    globalClientRevision = revision;
    setInterval(checkForGlobalUpdate, 10000);
    console.log("AutoUpdate started.");
}
