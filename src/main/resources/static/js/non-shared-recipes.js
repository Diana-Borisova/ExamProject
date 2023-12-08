
const recipesList = document.getElementById('cardsList');
const id = document.getElementById('user').getAttribute('user');
const recipes = [];
fetch("http://localhost:8080/recipes/api/non-shared")
    .then(response => response.json())
    .then(data => {
        recipes.push(...data.filter(recipe => recipe.shared == false));
    })
    .then(() => displayNonSharedRecipes(recipes))
    .catch(error => {
        console.error('Error fetching data:', error);
    });

const displayNonSharedRecipes = (recipes) => {
    displayRecipes(recipes);

}

