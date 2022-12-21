document.querySelector('#provincias').addEventListener('click',function(){
    obtenerProvincias();
});
document.querySelector('#municipios').addEventListener('click',function(){
    obtenerMunicipios();
});

function obtenerProvincias(){
    let url = 'http://localhost:9000/provincias';

    const api = new XMLHttpRequest();
    api.open('GET',url,true);
    api.send();
    api.onreadystatechange = function(){
        if(this.status == 200 && this.readyState == 4){
            let provincias = JSON.parse(this.responseText);
            let resultado = document.querySelector('#provincias');
            resultado.innerHTML = '';
            for(prov of provincias){
                resultado.innerHTML += `<option value="${prov.id}">${prov.nombre}</option>`;
            }
        }
    };
};

function obtenerMunicipios(){
    console.log(document.querySelector('#provincias').textContent);
};