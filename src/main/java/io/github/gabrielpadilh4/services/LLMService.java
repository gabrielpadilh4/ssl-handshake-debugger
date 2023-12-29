package io.github.gabrielpadilh4.services;

import java.util.HashMap;
import java.util.Map;

import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiModelName;

/**
 * @author pedro-hos@outlook.com
 */
public class LLMService {
	
	public static String askChatGPT(String error, String apiKey) {
		PromptTemplate promptTemplate = PromptTemplate.from("How to fix {{error}}");
		Map<String, Object> variables = new HashMap<>();
		variables.put("error", error);
		Prompt prompt = promptTemplate.apply(variables);
		return withModel(apiKey).generate(prompt.text());
	}
	
	private static ChatLanguageModel withModel(String apiKey) {
		return OpenAiChatModel.builder()
				  .apiKey(apiKey)
				  .modelName(OpenAiModelName.GPT_3_5_TURBO)
				  .temperature(0.3)
				  .build();
	}

}
