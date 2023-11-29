
const recipesList = document.getElementById('cardsList');
const id = document.getElementById('user').getAttribute('user');
const recipes = [];
fetch("http://localhost:8080/recipes/api/owned")
    .then(response => response.json())
    .then(data => {
        recipes.push(...data.filter(recipe => recipe.recipeOwner === id));
    })
    .then(() => displayMyRecipes(recipes))
    .catch(error => {
        console.error('Error fetching data:', error);
    });

const displayMyRecipes = (recipes) => {
    displayRecipes(recipes);

}

