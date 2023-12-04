const tbody = document.querySelector('tbody');
const allRecipes = [];
const id = document.getElementById('recipe').getAttribute('recipeId');
fetch('http://localhost:8080/recipes/details/manage/'+id).then(r => r.json()).then(data => {
    for (let d of data) {
        allRecipes.push(d);
    }
}).then(p => showRecipes(allRecipes));



function showRecipes(recipes) {
    tbody.innerHTML = '';
    for (let r of recipes) {
        const id = document.createElement('td');
        id.innerText = r.id;
        const title = document.createElement('td');
        title.innerText = r.title;
        const shared = document.createElement('td');
        shared.innerText = r.shared;
        const cookingTime = document.createElement('td');
        cookingTime.innerText = r.cookingTime;
        const description = document.createElement('td');
        description.innerText = r.description;
        const btn = document.createElement('td');
        const a = document.createElement('a');
        a.type = 'button';
        a.className = 'btn btn-danger w-100';
        a.href = '/recipes/details/manage/' + r.id;
        a.innerText = 'EDIT';
        btn.appendChild(a);
        const tr = document.createElement('tr');
        tr.appendChild(id);
        tr.appendChild(title);
        tr.appendChild(shared);
        tr.appendChild(cookingTime);
        tr.appendChild(description);
        tr.appendChild(btn);
        tbody.appendChild(tr);
    }
}
