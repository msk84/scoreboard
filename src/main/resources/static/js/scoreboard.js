/* Scoreboard JavaScript */
function updateGameScore(matchId, gameId, isPartyHome, isAdd) {

    let currentScoreHome = $("#game_" + gameId + "_scoreHome").text() * 1;
    let currentScoreGuest = $("#game_" + gameId + "_scoreGuest").text() * 1;

    if(isAdd) {
        isPartyHome ? currentScoreHome++ : currentScoreGuest++;
    }
    else {
        isPartyHome ? currentScoreHome-- : currentScoreGuest--;
    }

    if((currentScoreHome >= 0) && (currentScoreHome <= 3) && (currentScoreGuest >= 0) && (currentScoreGuest <= 3) && (currentScoreHome + currentScoreGuest <= 5)) {
        $.ajax({
            type: "POST",
            url: "/api/match/" + matchId + "/game/" + gameId + "/" + (isAdd ? "incrementScore" : "decrementScore") + "/" + (isPartyHome ? "Home" : "Guest"),
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (match) {
                $("#match_" + matchId + "_scoreHome").text(match.scoreHome);
                $("#match_" + matchId + "_scoreGuest").text(match.scoreGuest);

                let gameToUpdate = match.games.filter(function(game){
                    return game.id == gameId;
                })[0];

                if (isPartyHome) {
                    $("#game_" + gameId + "_scoreHome").text(gameToUpdate.scoreHome);
                } else {
                    $("#game_" + gameId + "_scoreGuest").text(gameToUpdate.scoreGuest);
                }
            },
            error: function (errMsg) {
                console.log("updateGameScore :: " + errMsg);
            }
        });
    }
    else {
        console.log("updateGameScore::Tried to set invalid game score. - MatchId: " + matchId + ", GameId: " + gameId + ", ScoreHome: " + currentScoreHome + ", ScoreGuest: " + currentScoreGuest + ", isPartyA: " + isPartyHome + ", isAdd: " + isAdd);
    }
}

let globalClientRevision = 0;
function checkForGlobalUpdate() {
    $.get("/api/tool/getServerRevision", function(data, status) {
        if(status === "success") {
            let globalServerRevision = data * 1;
            if(globalServerRevision > globalClientRevision) {
                console.log("checkForGlobalUpdate::New version. Let's reload.");
                location.reload();
            }
            else {
                console.log("Nothing to update. :: GlobalClientRevision: " + globalClientRevision);
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
