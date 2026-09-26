# Reconhecimento de Imagens

## Requisitos

- JDK 25
- Apache Maven 3.9 ou superior

Os fontes existentes usam Windows-1252. O build Maven e as preferencias do Eclipse declaram essa codificacao para preservar os textos legados.

## Compilar e testar

Na raiz do repositorio, execute:

```powershell
mvn clean test
```

O codigo compilado fica em `target/classes` e os testes de caracterizacao em `test/`.

## Executar

Na raiz do repositorio, execute:

```powershell
java -cp target/classes Main.MainRecImagens
```

Os caminhos dos arquivos de imagem e icones ainda dependem do diretorio de execucao atual; execute o comando a partir da raiz do projeto.

