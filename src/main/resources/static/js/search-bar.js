const recipesList = document.getElementById('cardsList');
const titleInput = document.getElementById('title');
const starsInput = document.getElementById('stars');
const preparationInput = document.getElementById('preparation');
const searchBtn = document.getElementById('searchBtn');
const allRecipes = [];
fetch("http://localhost:8080/recipes/all")
    .then(response => response.json())
    .then(data => {
        for (let d of data) {
            allRecipes.push(d);
        }
    })
    .then(() => displayRecipes(allRecipes)) // Move displayHotels inside the final then block
    .catch(error => {
        console.error('Error fetching data:', error);
    });

searchBtn.addEventListener('click', (e) => {
    const recipeSearchingCharacters = titleInput.value.toLowerCase();
    const preparationSearchingCharacters = preparationInput.value.toLowerCase();
    const stars = starsInput.value;
    console.log(allRecipes);
    let filteredRecipes = allRecipes.filter(recipe => {
        if (stars === '') {
            return recipe.title.toLowerCase().includes(recipeSearchingCharacters)
                 && recipe.preparation.toLowerCase().includes(preparationSearchingCharacters);
        }
        return recipe.title.toLowerCase().includes(recipeSearchingCharacters)
          && recipe.preparation.toLowerCase().includes(preparationSearchingCharacters)
            && recipe.stars === stars;
    });
    console.log(filteredRecipes);
    displayRecipes(filteredRecipes);
});


const displayRecipes = (recipes) => {
    recipesList.innerHTML = '';
    let row = [];
    for (let i = 0; i < recipes.length; i++) {
        let card = document.createElement('div');
        card.classList.add('card');
        card.classList.add('my-card');

        // Apply flex styles for centering
        card.style.display = 'flex';
        card.style.flexDirection = 'column';
        card.style.justifyContent = 'center';
        card.style.alignItems = 'center';

        // Add margin to create space between cards
        card.style.marginBottom = '20px'; // Adjust the value to set your desired space

        let img = document.createElement('img');
        img.className = 'card-img-top card-image';
        img.src = recipes[i].image;
        img.alt = 'Card image cap';

        // Set specific width and height
        img.width = 200;
        img.height = 250;

        let cardBody = document.createElement('div');
        cardBody.className = 'card-body';

        let textContainer = document.createElement('div');
        textContainer.style.display = 'flex';
        textContainer.style.flexDirection = 'column';
        textContainer.style.alignItems = 'center';

        let cardTitle = document.createElement('h5');
        cardTitle.className = 'card-title';
        cardTitle.innerText = recipes[i].title;
        cardTitle.style.fontWeight = 'bold';
        cardTitle.style.fontSize = '28px';

        let cardText = document.createElement('p');
        cardText.className = 'card-text';
        cardText.innerText = recipes[i].preparation;

        let buttonContainer = document.createElement('div');
        buttonContainer.style.display = 'flex';
        buttonContainer.style.justifyContent = 'center'; // Center the button horizontally
        buttonContainer.style.marginTop = '10px'; // Adjust the value to set your desired space

        let a = document.createElement('a');
        a.className = 'btn btn-success';
        a.href = '/recipes/details/' + recipes[i].id;
        a.innerText = 'See more...';

        // Apply flex styles for centering
        cardTitle.style.margin = '0'; // Reset default margin
        cardText.style.margin = '0';

        textContainer.appendChild(cardTitle);
        textContainer.appendChild(cardText);
        buttonContainer.appendChild(a);

        cardBody.appendChild(textContainer);
        cardBody.appendChild(buttonContainer);
        card.appendChild(img);
        card.appendChild(cardBody);
        row.push(card);

        if ((i + 1) % 3 === 0 || recipes.length - i === 1) {
            let cardGroup = document.createElement('div');
            cardGroup.classList.add('card-group');
            cardGroup.classList.add('align-content-center');
            cardGroup.classList.add('mx-auto');
            for (let j = 0; j < row.length; j++) {
                cardGroup.appendChild(row[j]);
            }
            recipesList.appendChild(cardGroup);
            row = [];
        }
    }
}

