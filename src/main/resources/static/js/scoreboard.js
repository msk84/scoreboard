/* Scoreboard JavaScript */
function updateGameScore(matchId, gameId, isPartyHome, isAdd) {

    let scoreA = $("#game_" + gameId + "_scoreHome").text() * 1;
    let scoreB = $("#game_" + gameId + "_scoreGuest").text() * 1;

    if(isAdd) {
        isPartyHome ? scoreA++ : scoreB++;
    }
    else {
        isPartyHome ? scoreA-- : scoreB--;
    }

    if((scoreA >= 0) && (scoreA <= 10) && (scoreB >= 0) && (scoreB <= 10) && (scoreA + scoreB <= 10)) {
        $.ajax({
            type: "POST",
            url: "/api/match/" + matchId + "/game/" + gameId + "/" + (isAdd ? "incrementScore" : "decrementScore") + "/" + (isPartyHome ? "Home" : "Guest"),
            data: "",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (match) {
                console.log(JSON.stringify(match));

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
        console.log("updateGameScore::Tried to set invalid game score. - MatchId: " + matchId + ", GameId: " + gameId + ", ScoreA: " + scoreA + ", ScoreB: " + scoreB + ", isPartyA: " + isPartyHome + ", isAdd: " + isAdd);
    }
}
