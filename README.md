# Minecraft Plugins
Todos os plugins que estou desenvolvendo por diversão para servidores de Minecraft usando Spigot. Se quiser aprender a programar plugins, recomendo [pela IDE IntelliJ](https://www.youtube.com/watch?v=v4zbqtpLaS4&list=PLfu_Bpi_zcDNEKmR82hnbv9UxQ16nUBF7), que facilita muito, mas pode ser feito com [IDEs diversas, como Eclipse](https://www.youtube.com/watch?v=r4W4drYdb4Q).

## LifeBond
Tem o objetivo de compartilhar o dano entre players do mesmo grupo ou "vínculo". Se um jogador do mesmo vínculo que você recebe dano, você recebe o mesmo dano, mas cada um recupera a vida baseado na própria fome.

### Comandos
  - `/criar <nome>`: Cria um vínculo de nome <nome>
  - `/entrar <nome>`: Entra no vínculo <nome>
  - `/vinculos`: Exibe vínculos existentes (vínculos são automaticamente deletados quando todos os jogadores saem dele)
  - `/meuvinculo`: Informa vínculo do player
  - `/sair`: Retira o player de seu vínculo atual

## DeathMessenger
Plugin extremamente simples idealizado pelo [Nikolas](https://github.com/NikolasTola). Consiste em alterar as mensagens de morte enviadas no chat, enviando a posição em que o jogador morreu.
