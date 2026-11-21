# Calopsita Mod

Um mod para **Minecraft Java Edition 26.3** (Fabric) que adiciona a **Calopsita**, um pet pássaro domesticável.

## Funcionalidades

- Nova entidade `calopsita:calopsita` — um passarinho domesticável
- **Domesticação**: use sementes (trigo, melancia, abóbora, beterraba) — igual ao periquito vanilla
- Uma vez domesticada, a Calopsita:
  - Segue o dono
  - Senta/levanta com clique direito
  - Anda no ombro do jogador
  - Pode ser levada com laço
- **Ovo Gerador de Calopsita** na aba de spawn eggs do criativo
- Nomeável com Etiqueta
- Nome traduzido em Português (`pt_br`) e Inglês (`en_us`)

## Como usar

1. Compile com `./gradlew build` (requer **JDK 25**)
2. O jar sai em `build/libs/calopsita-1.0.0.jar`
3. Coloque o jar + [Fabric API](https://modrinth.com/mod/fabric-api) (versão para 26.3) + Fabric Loader na pasta `mods` do Minecraft 26.3

No jogo:
- Pegue o **Ovo Gerador de Calopsita** (aba Spawn Eggs)
- Use **sementes** na Calopsita selvagem para domesticá-la
- Clique direito para sentar/levantar; ela segue você quando está livre

## Estrutura

```
src/main/java/com/calopsita/
├── CalopsitaMod.java          # registro da entidade e do spawn egg
└── entity/CalopsitaEntity.java # entidade (estende Parrot do vanilla)
src/client/java/com/calopsita/client/
└── CalopsitaClient.java       # registro do renderer
src/main/resources/
├── fabric.mod.json
└── assets/calopsita/lang/     # traduções
```

## Personalização

A Calopsita reutiliza o modelo, texturas, animações e sons do periquito vanilla. Para dar uma aparência própria:

1. Crie um modelo `.geo.json` e textura `.png` (ex.: com Blockbench)
2. Crie um `CalopsitaRenderer` estendendo `ParrotRenderer` e sobrescreva `getTextureLocation()`
3. Registre-o no `CalopsitaClient` com `EntityRendererRegistry.register`

## Build

```bash
JAVA_HOME=/caminho/para/jdk-25 ./gradlew build
```

Requer **JDK 25** (Minecraft 26.3).
