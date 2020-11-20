// 21.00

let min = 1,
    max = 10,
    winningNum = getRandomNum(min,max),
    guessesLeft = 3;

const game = document.querySelector('#game'),
      minNum = document.querySelector('.min-num'),
      maxNum = document.querySelector('.max-num'),
      guessBtn = document.querySelector('#guess-btn'),
      guessInput = document.querySelector('#guess-input')
      message = document.querySelector('.message');

minNum.textContent = min
maxNum.textContent = max

//play again event listener
game.addEventListener('mousedown', function(e){
  if(e.target.className === 'play-again'){
    window.location.reload()
  }
})

guessBtn.addEventListener('click', function(){
  let guess = parseInt(guessInput.value)
  if(isNaN(guess) || guess < min || guess > max){
    setMessage(`Please enter a number between ${min} and ${max}`, 'red')
  }
  if(guess === winningNum){
    gameOver(true,`${winningNum} is correct, YOU WIN!`)
  }else {
    guessesLeft -= 1
    if(guessesLeft === 0){
      gameOver(false, `Game over, you lost. The correct number was ${winningNum}.`)
      
    }else {
      //game continues
      setMessage(`${guess} is not correct, ${guessesLeft} guesses left`, 'red')
      guessInput.value = ""
    }
  }

})

function gameOver(won, msg){
  let color;
  won === true ? color = 'green' : color= 'red'
  guessInput.disabled = true
  message.style.color = color
  guessInput.style.borderColor = color
  guessInput.style.borderWidth = '3px'
  setMessage(msg)
  guessBtn.value = 'Play again?'
  guessBtn.className += 'play-again'

}

function getRandomNum(min,max){
  return Math.floor(Math.random()*(max-min+1)+min)
}

function setMessage(msg, color){
  message.style.color = color
  message.textContent = msg 

}

