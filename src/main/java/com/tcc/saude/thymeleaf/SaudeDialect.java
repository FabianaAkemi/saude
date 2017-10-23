package com.tcc.saude.thymeleaf;

import java.util.HashSet;
import java.util.Set;

import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import com.tcc.saude.thymeleaf.processor.ClassForErrorAttributeTagProcessor;
import com.tcc.saude.thymeleaf.processor.MenuAttributeTagProcessor;
import com.tcc.saude.thymeleaf.processor.MessageElementProcessor;
import com.tcc.saude.thymeleaf.processor.OrderElementTagProcessor;
import com.tcc.saude.thymeleaf.processor.PaginationElementTagProcessor;

public class SaudeDialect extends AbstractProcessorDialect{

	public SaudeDialect() {
		super("TCC saude", "saude", StandardDialect.PROCESSOR_PRECEDENCE);
		
	}

	@Override
	public Set<IProcessor> getProcessors(String dialectPrefix) {
		final Set<IProcessor> processadores = new HashSet<>();
		processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
		processadores.add(new MessageElementProcessor(dialectPrefix));
		processadores.add(new OrderElementTagProcessor(dialectPrefix));
		processadores.add(new PaginationElementTagProcessor(dialectPrefix));
		processadores.add(new MenuAttributeTagProcessor(dialectPrefix));

		return processadores;
	}

}
