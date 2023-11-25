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
        const type = document.createElement('td');
        type.innerText = r.title;
        const name = document.createElement('td');
        name.innerText = r.shared;
        const price = document.createElement('td');
        price.innerText = r.cookingTime;
        const singleBeds = document.createElement('td');
        singleBeds.innerText = r.description;
        // const twinBeds = document.createElement('td');
        // twinBeds.innerText = r.products;
        // const count = document.createElement('td');
        // count.innerText = r.count;
        const btn = document.createElement('td');
        const a = document.createElement('a');
        a.type = 'button';
        a.className = 'btn btn-danger w-100';
        a.href = '/recipes/edit-recipe/' + r.id;
        a.innerText = 'EDIT';
        btn.appendChild(a);
        const tr = document.createElement('tr');
        tr.appendChild(id);
        tr.appendChild(type);
        tr.appendChild(name);
        tr.appendChild(price);
        tr.appendChild(singleBeds);
        // tr.appendChild(twinBeds);
        // tr.appendChild(count);
        tr.appendChild(btn);
        tbody.appendChild(tr);
    }
}
