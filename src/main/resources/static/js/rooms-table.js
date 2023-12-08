const tbody = document.querySelector('tbody');
const allPictures = [];
const id = document.getElementById('recipe').getAttribute('recipeid');
fetch('http://localhost:8080/recipes/delete/'+id).then(r => r.json()).then(data => {
    for (let d of data) {
        allPictures.push(d);
    }
}).then(p => showPictures(allPictures));



function showPictures(pictures) {
    tbody.innerHTML = '';
    for (let p of pictures) {
        const id = document.createElement('td');
        id.innerText = p.id;
        // const type = document.createElement('td');
        // type.innerText = r.type;
        // const name = document.createElement('td');
        // name.innerText = r.name;
        // const price = document.createElement('td');
        // price.innerText = r.price;
        // const singleBeds = document.createElement('td');
        // singleBeds.innerText = r.singleBedsCount;
        // const twinBeds = document.createElement('td');
        // twinBeds.innerText = r.twinBedsCount;
        // const count = document.createElement('td');
        // count.innerText = r.count;
        const btn = document.createElement('td');
        const a = document.createElement('a');
        a.type = 'button';
        a.className = 'btn btn-danger w-100';
        a.href = '/recipes/edit-picture/' + p.id;
        a.innerText = 'EDIT';
        btn.appendChild(a);
        const tr = document.createElement('tr');
        tr.appendChild(id);
        // tr.appendChild(type);
        // tr.appendChild(name);
        // tr.appendChild(price);
        // tr.appendChild(singleBeds);
        // tr.appendChild(twinBeds);
        // tr.appendChild(count);
        tr.appendChild(btn);
        tbody.appendChild(tr);
    }
}
