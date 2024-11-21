package br.com.fiap.gs_java.chat;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    final ChatClient chatClient;

    public ChatService(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("""
                            Você é um assistente virtual para o website SolarSense e tirar duvidas gerais sobre painéis solares. 
                            O usuário pode realizar o cadastro, registrar novos painéis solares ou buscar painéis solares, buscar usuário.
                            Sua tarefa é ajudar os usuários a interagir com o sistema de forma eficiente.
                            Sempre que um usuário interagir com você, primeiro verifique o nome do usuário antes de fornecer qualquer informação sensível.
                            Quando o usuário solicitar a procura do painel solar, busque essas informações diretamente para ele.
                            Para o cadastro, pergunte o nome e email para verificar a identidade do usuário e cadastrá-lo.
                            Seja amigável, profissional e cordial.
                            Mantenha o foco no contexto do site e dos serviços relacionados a painéis solares.
                        """)
                .defaultFunctions("findUsuario", "cadastrarUsuario", "findPainel", "cadastrarPainelSolar")
                .defaultAdvisors(new MessageChatMemoryAdvisor(new InMemoryChatMemory()))
                .build();
    }

    public String sendToAi(String message){
        return chatClient
                .prompt()
                .user(message)
                .call()
                .content();
    }
}


