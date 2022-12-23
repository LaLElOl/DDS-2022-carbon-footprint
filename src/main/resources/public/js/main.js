/*document.querySelector('#provincias').addEventListener('change',(event)=>function(){
    obtenerMunicipios();
});*/

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
    var provId = document.getElementById("provincias").value;
    if(provId == null || provId == ''){
        alert('No hay una provincia seleccionada');
    }else{
        let url = `http://localhost:9000/municipios/${provId}`;

        const api = new XMLHttpRequest();
        api.open('GET',url,true);
        api.send();
        api.onreadystatechange = function(){
            if(this.status == 200 && this.readyState == 4){
                let municipios = JSON.parse(this.responseText);
                let resultado = document.querySelector('#municipios');
                resultado.innerHTML = '';
                for(mun of municipios){
                    resultado.innerHTML += `<option value="${mun.id}">${mun.nombre}</option>`;
                }
            }
        };
    }
};

function obtenerLocalidades(){
    var munId = document.getElementById("municipios").value;
    if(munId == null || munId == ''){
        alert('No hay un municipio seleccionado');
    }else{
        let url = `http://localhost:9000/localidades/${munId}`;

        const api = new XMLHttpRequest();
        api.open('GET',url,true);
        api.send();
        api.onreadystatechange = function(){
            if(this.status == 200 && this.readyState == 4){
                let localidades = JSON.parse(this.responseText);
                let resultado = document.querySelector('#localidades');
                resultado.innerHTML = '';
                for(loc of localidades){
                    resultado.innerHTML += `<option value="${loc.id}">${loc.nombre}</option>`;
                }
            }
        };
    }
};