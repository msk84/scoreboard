/* Scoreboard JavaScript - Match set score */
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
                $("#match_" + matchId + "_status").text(match.status);

                if(match.status == "RUNNING") {
                    $("#match_" + matchId + "_score").addClass("inProgress");
                }
                else {
                    $("#match_" + matchId + "_score").removeClass("inProgress");
                }

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

function deleteMatch(matchId) {
    console.log("Deleting Match. :: MatchId: " + matchId);
    $.ajax({
        type: "DELETE",
        url: "/api/match/delete/" + matchId,
        success: function (value) {
            console.log("Match successfully deleted.");
            location.replace("/match/overview");
        },
        error: function (errMsg) {
            console.log("Failed to delete match. :: Error: " + errMsg);
        }
    });
}