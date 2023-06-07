const cards = document.querySelectorAll('.card');

let flippedCard = false;
let lockBoard = false;
let card1, card2;

function flipCard() {
    if (lockBoard === true) {
        return;
    }

    if (this === card1) {
        return;
    }

    this.classList.add('flip');

    if (!flippedCard) {
        flippedCard = true;
        card1 = this;

        return;
    }

    card2 = this;

    checkCards();
}

function checkCards() {
    let match = false;
    if (card1.dataset.val === card2.dataset.val) {
        match = true;
    }

    if (match === true) {
        disableCards();
    } else {
        unflipCards();
    }
}

function disableCards() {
    card1.removeEventListener('click', flipCard);
    card2.removeEventListener('click', flipCard);

    resetBoard();
}

function unflipCards() {
    lockBoard = true;

    setTimeout(() => {
        card1.classList.remove('flip');
        card2.classList.remove('flip');

        resetBoard();
    }, 1500);
}

function resetBoard() {
    flippedCard = false;
    lockBoard = false;
    card1 = null;
    card2 = null;
}

(function shuffle() {
    cards.forEach(card => {
        let pos = Math.floor(Math.random() * 12);
        card.style.order = pos;
    });
})();

cards.forEach(card => card.addEventListener('click', flipCard));