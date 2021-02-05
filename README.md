# NextTasks

Um simples sistema de automação de comandos, crie tarefas para serem executadas na exata data configurada. Contém uma pequena [API](https://github.com/NextPlugins/NextTasks/blob/main/src/main/java/com/nextplugins/tasks/api/NextTasksAPI.java) para desenvolvedores. [Prints in-game](https://imgur.com/a/k69BHiR)

## Comandos
|Comando               |Aliases   |Descrição           |Permissão             |
|----------------------|----------|--------------------|----------------------|
|/tasks                |/tarefas  |Veja todas as tarefas agendadas.|`nexttasks.command.tasks`|

## Download

Você pode encontrar o plugin pronto para baixar [**aqui**](https://github.com/NextPlugins/NextTasks/releases), ou se você quiser, pode optar por clonar o repositório e dar
build no plugin com suas alterações.

## Configuração
O plugin conta com dois arquivos de configuração `config.yml` e `tasks.yml`, em que você pode configurar mensagens, agendar tarefas e outras opções.

## Dependências
O plugin não precisa de nenhuma dependência. As dependências de desenvolvimento são automaticamente baixadas na primeira inicialização do plugin pela da tecnologia `PDM`

### Tecnologias usadas
-   [PDM](https://github.com/knightzmc/pdm) - Baixa as dependências de desenvolvimento assim que o plugin é ligado pela primeira vez.
-   [Quartz](http://www.quartz-scheduler.org/) - Biblioteca robusta para agendamento de tarefas.

**APIs e Frameworks**

-   [command-framework](https://github.com/SaiintBrisson/command-framework) - Framework para criação e gerenciamento de comandos.
