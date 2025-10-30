🎬 App Filmes

O App Filmes é um aplicativo Android desenvolvido em Java que consome a API pública do The Movie Database (TMDb) para pesquisar filmes e exibir informações e pôsteres em tempo real.

 Funcionalidades
 > Busca de filmes por título usando a API do TMDb.
 > Exibição de pôsteres e nomes dos filmes em uma lista (RecyclerView).
 > Ao clicar em um filme, o app mostra detalhes completos (sinopse, data, nota, etc.).
 > Consumo da API com Retrofit 2 e carregamento de imagens com Glide.
 > Estrutura de código modular e fácil de expandir.


 Tecnologias utilizadas
 > Java (Android)
 > Retrofit 2 — comunicação com a API REST
 > Glide — carregamento e cache de imagens
 > RecyclerView — exibição de lista de filmes
 > Material Design Components — interface moderna e responsiva

Estrutura do projeto

Pacotes Java:
app/ 
└── java/ 
└── com.example.myapplicationfilmespiderman/ 
├── Api/ 
│ ├── RetrofitClient.java
│ └── ApiService.java  
│ ├── Adapter/ 
│ └── MovieAdapter.java 
│ ├── model/ 
│ ├── Movie.java
│ └── MovieResponse.java 
├── MainActivity.java 
└── DetailActivity.java 


Recursos XML
app/
 └── res/
      ├── layout/
      │    ├── activity_main.xml 
      │    ├── item_movie.xml      
      │    └── activity_detail.xml  
      │
      ├── drawable/         
      └── values/          


Configuração da API

O aplicativo usa a API do The Movie Database (TMDb).

Crie (ou use) sua API Key gratuita e adicione no arquivo:
app/src/main/java/com/example/myapplicationfilmespiderman/ApiKey.java

Como executar o projeto

Clone este repositório:
git clone https://github.com/seuusuario/App_Filmes.git

Abra o projeto no Android Studio.
No arquivo ApiKey.java, cole sua chave da API.
Faça o Sync Gradle e execute o app em um emulador ou dispositivo físico.
Digite o nome de um filme e toque em Buscar — os resultados aparecerão na tela.

Requisitos

Android Studio (versão 2023+)
SDK Android 24+
Conexão com internet (para carregar dados da API)




