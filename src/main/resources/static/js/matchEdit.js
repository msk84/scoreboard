/* Scoreboard JavaScript - Match create/edit */
function removeLastGame() {
    let gamesList = document.getElementById("gamesList");
    if(gamesList.children.length > 1) {
        let lastGame = gamesList.lastElementChild;
        lastGame.remove();
    }
    else {
        alert("Only one game left.");
    }
}

function addAndAppendGame() {
    let gamesList = document.getElementById("gamesList");

    if(gamesList.children.length >= 20) {
        console.debug("Let stop at 20 games...");
    }
    else {
        let clone = gamesList.firstElementChild.cloneNode(true);
        let cloneIndex = gamesList.children.length;

        clone.id = "match-" + cloneIndex;
        clone.firstElementChild.innerHTML = "Game " + (cloneIndex + 1);

        let divs = clone.getElementsByTagName("div");
        divs[0].firstElementChild.id = "games" + cloneIndex + ".partyHome";
        divs[0].firstElementChild.name = "games[" + cloneIndex + "].partyHome";
        divs[0].firstElementChild.value = "";
        divs[1].firstElementChild.id = "games" + cloneIndex + ".partyGuest";
        divs[1].firstElementChild.name = "games[" + cloneIndex + "].partyGuest";
        divs[1].firstElementChild.value = "";
        divs[2].firstElementChild.id = "games" + cloneIndex + ".id";
        divs[2].firstElementChild.name = "games[" + cloneIndex + "].id";
        divs[2].firstElementChild.value = 0;
        divs[2].lastElementChild.id = "games" + cloneIndex + ".index";
        divs[2].lastElementChild.name = "games[" + cloneIndex + "].index";
        divs[2].lastElementChild.value = cloneIndex;

        gamesList.append(clone);
    }
}
