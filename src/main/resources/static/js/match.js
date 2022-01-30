/* Scoreboard JavaScript - Match set score */
function updateGameScore(matchId, gameId, isPartyHome, isAdd) {
    let currentScoreHome = $("#game_" + gameId + "_scoreHome").text();
    let currentScoreGuest = $("#game_" + gameId + "_scoreGuest").text();

    currentScoreHome = isNaN(currentScoreHome) ? 0 : currentScoreHome * 1;
    currentScoreGuest = isNaN(currentScoreGuest) ? 0 : currentScoreGuest * 1;

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
                // update match score
                $("#match_" + matchId + "_scoreHome").text(match.scoreHome);
                $("#match_" + matchId + "_scoreGuest").text(match.scoreGuest);
                $("#match_" + matchId + "_status").text(match.status);

                if(match.status == "RUNNING") {
                    $("#match_" + matchId + "_score").addClass("inProgress");
                }
                else {
                    $("#match_" + matchId + "_score").removeClass("inProgress");
                }

                // update game score
                let gameToUpdate = match.games.filter(function(game){
                    return game.id == gameId;
                })[0];

                $("#game_" + gameId + "_scoreHome").text(gameToUpdate.scoreHome);
                $("#game_" + gameId + "_scoreGuest").text(gameToUpdate.scoreGuest);

                if(gameToUpdate.status == "RUNNING") {
                    $("#game_" + gameId + "_score").addClass("inProgress");
                }
                else {
                    $("#game_" + gameId + "_score").removeClass("inProgress");
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

function addGameHighlight(matchId, gameId, isPartyHomeString, highlight, highlightValue) {
    let isPartyHome = (isPartyHomeString === 'true');

    if(highlight === "HighFinish") {
        if(highlightValue < 100 || highlightValue >= 170) {
            console.error("Invalid Highlight 'HighFinish' with value '" + highlightValue + "'.");
            alert("Invalid Highlight 'HighFinish' with value '" + highlightValue + "'.");
            return;
        }
    }
    else if(highlight === "ShortGame") {
        if(highlightValue < 9 || highlightValue > 18) {
            console.error("Invalid Highlight 'ShortGame' with value '" + highlightValue + "'.");
            alert("Invalid Highlight 'ShortGame' with value '" + highlightValue + "'.");
            return;
        }
    }

    console.log("gameId: " + gameId + "; isPartyHome: " + isPartyHome + "; highlight: " + highlight + "; highlightValue: " + highlightValue);
    $.ajax({
        type: "POST",
        url: "/api/match/" + matchId + "/game/" + gameId + "/addHighlight/" + (isPartyHome ? "Home" : "Guest") + "/" + highlight + (highlight === 'OneEighty' ? "" : "/" + highlightValue),
        data: "",
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: function (match) {
            if(isPartyHome) {
                if(highlight === "OneEighty") {
                    $("#game_" + gameId + "_highlightsHome").append("<span><i class=\"bi-badge-8k fs-3\"></i></span>");
                }
                else if(highlight === "HighFinish") {
                    $("#game_" + gameId + "_highlightsHome").append("<span><i class=\"bi-bullseye fs-3\">" + highlightValue + "</i></span>");
                }
                else if(highlight === "ShortGame") {
                    $("#game_" + gameId + "_highlightsHome").append("<span><i class=\"bi-arrows-angle-contract fs-3\">" + highlightValue + "</i></span>");
                }
            }
            else {
                if(highlight === "OneEighty") {
                    $("#game_" + gameId + "_highlightsGuest").append("<span><i class=\"bi-badge-8k fs-3\"></i></span>");
                }
                else if(highlight === "HighFinish") {
                    $("#game_" + gameId + "_highlightsGuest").append("<span><i class=\"bi-bullseye fs-3\">" + highlightValue + "</i></span>");
                }
                else if(highlight === "ShortGame") {
                    $("#game_" + gameId + "_highlightsGuest").append("<span><i class=\"bi-arrows-angle-contract fs-3\">" + highlightValue + "</i></span>");
                }
            }
        },
        error: function (errMsg) {
            console.log("addGameHighlight :: " + errMsg);
        }
    });
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
