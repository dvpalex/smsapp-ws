package br.com.cardif.sms.agents;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.cardif.sms.model.Agent;

public class ScheduleServletListenerTest {

	private ScheduleServletListener obj;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 obj = new ScheduleServletListener();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testInit() {
		

		assertNotNull(obj);
		
	}
	
	/*
	@Test
	public void testLoadAgentFake(){
	
		Set<Agent> lst = obj.loadAgentFake();
		assertEquals(2, lst.size());
	}*/

	
	
	
}
