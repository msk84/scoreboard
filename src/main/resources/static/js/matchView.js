let highlightModal = document.getElementById('ModifyGameHighlights')
highlightModal.addEventListener('show.bs.modal', function (event) {
    let button = event.relatedTarget;
    let isPartyHome = button.getAttribute('data-bs-party-home');
    let gameId = button.getAttribute('data-bs-gameid');

    let modalGameId = highlightModal.querySelector('#modalGameId');
    modalGameId.value = gameId;
    let modalIsPartyHome = highlightModal.querySelector('#modalIsPartyHome');
    modalIsPartyHome.value = isPartyHome;

    let modalAddHighfinishValue = highlightModal.querySelector('#addHighfinishValue');
    modalAddHighfinishValue.value = "";
    let modalAddShortgameValue = highlightModal.querySelector('#addShortgameValue');
    modalAddShortgameValue.value = "";
})
