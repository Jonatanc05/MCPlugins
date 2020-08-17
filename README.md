# Minecraft Plugins
Todos os plugins que estou desenvolvendo por diversão para servidores de Minecraft usando Spigot. Se quiser aprender a programar plugins, recomendo [pela IDE IntelliJ](https://www.youtube.com/watch?v=v4zbqtpLaS4&list=PLfu_Bpi_zcDNEKmR82hnbv9UxQ16nUBF7), que facilita muito, mas pode ser feito com [IDEs diversas, como Eclipse](https://www.youtube.com/watch?v=r4W4drYdb4Q).

Qualquer ajuda é bem vinda então fique a vontade pra dar Pull Requests, há variás ideias de plugin [nesse doc](https://docs.google.com/document/d/1ZTrTMG60Frp40IZoICb1P4azm_syNGg3sqlkti-0hNI/edit?usp=sharing) que podem ser implementadas.

## Como rodar e testar alterações
Para compilar os plugins basta rodar o arquivo Main de cada um com a [biblioteca do spigot](https://scalacube.com/store/minecraft/spigot-1.16.2/download) como dependência externa.

Para rodar os plugins você deve ter um servidor, [online](server.pro) ou [local](https://www.youtube.com/watch?v=d1vzYR0I22g), e adicionar o arquivo jar que compilou na pasta plugins, o servidor carregará o plugin ao iniciar novamente.

## Plugin LifeBond
Tem o objetivo de compartilhar o dano entre players do mesmo grupo ou "vínculo". Se um jogador do mesmo vínculo que você recebe dano, você recebe o mesmo dano, mas cada um recupera a vida baseado na própria fome.

### Comandos
  - `/criar <nome>`: Cria um vínculo de nome <nome>
  - `/entrar <nome>`: Entra no vínculo <nome>
  - `/vinculos`: Exibe vínculos existentes (vínculos são automaticamente deletados quando todos os jogadores saem dele)
  - `/meuvinculo`: Informa vínculo do player
  - `/sair`: Retira o player de seu vínculo atual

## Plugin DeathMessenger
Plugin extremamente simples idealizado pelo [Nikolas](https://github.com/NikolasTola). Consiste em alterar as mensagens de morte enviadas no chat, enviando a posição em que o jogador morreu.

## Plugin Classes
Disponibiliza várias opções de classes para o player assim que começa a jogar no servidor. Cada classe tem vantagens e desvantagens para sobreviver e explorar, oferencendo várias formas de jogar o jogo.

### Como criar uma classe
Se você quiser criar uma nova classe para o plugin é bem simples de começar. (O termo 'classe' pode ficar ambíguo então diferenciarei usando 'classe java' para as classes programáveis em java e 'classe jogável' para as classes que o jogador escolhe para jogar)

0. Crie uma classe java que implemente a interface *IClass* no pacote *me.jonata.classes*, sobrescrevendo seus métodos como estão descritos em *IClass.java*
0. A principal forma de adicionar funcionalidades é através de eventos. Para um método ser reconhecido pelo jogo como um evento ele precisa de quatro coisas:
  
	- Ter a notação @EventHandler antes da assinatura do método
	- Ter como único parâmetro um objeto que herda de [*org.bukkit.event.Event*](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/Event.html) (e. g. PlayerJoinEvent, EntityDamageEvent)
	- Estar em uma classe java que implementa [*org.bukkit.event.Listener*](https://hub.spigotmc.org/javadocs/spigot/org/bukkit/event/Event.html) (a interface *IClass* **já herda** de Listener, portanto **não é preciso implementar** novamente)
	- Esta classe *Listener* estar registrada pelo plugin (mostro como no item 4 da lista)
	
	- Exemplo:
		
			@EventHandler
			public void onPlayerConnect(PlayerJoinEvent e) {
				Player p = e.getPlayer();
				if(Main.playersClasses.get(p.getName()) != PlayableClass.SpeedRunner) return;
				p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
			}
			// Este evento é chamado quando um jogador se conecta ao servidor, ele verifica se o jogador escolheu a classe SpeedRunner e, caso positivo, adiciona a ele velocidade 1 por tempo infinito
0. Adicionar sua classe jogável ao enum *me.jonata.classes.PlayableClass*
0. Ir à classe java *Main*, no método *onEnable()* e duplicar a linha `classes.add(new Speedrunners());` inicializando a classe java que você criou ao invés de "Speedrunner"

Pronto, sua classe será reconhecida como uma classe jogável no plugin, aparecendo na GUI de escolha de classes e chamando seus eventos.

OBS: lembre-se que seus eventos são chamados para qualquer player, então verifique se o player que acionou o evento pertence à sua classe jogável antes de alterá-lo. Para a maioria dos eventos isso pode ser feito com 

			Main.playersClasses.get(p.getName()) != PlayableClass.MinhaClasse
(*Main.playersClasses* é um Hashmap que contém, para cada nome de jogador, a sua classe)
