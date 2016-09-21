package test.br.uefs.sgad.controller;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Test;

import main.br.uefs.sgad.controller.ArquivoNaoEncontradoException;
import main.br.uefs.sgad.controller.NaoEhPastaException;
import main.br.uefs.sgad.controller.PastaNaoEncontradaException;
import main.br.uefs.sgad.controller.SgadController;
import main.br.uefs.sgad.controller.TipoNaoEncontradoException;

public class ControllerTest {

	@Test
	public void testMountTreeSucess() throws PastaNaoEncontradaException, NaoEhPastaException, ArquivoNaoEncontradoException {
		SgadController controller = new SgadController();
		controller.mountPath("./src/test/pasta0");
		Iterator<Object> i = controller.seachByFolder("pasta4.1.1.1", 0);
		String str = "";
		while(i.hasNext()){
			str = str + i.next();
		}
		assertEquals("/pasta0/pasta4/pasta4.1/pasta4.1.1/pasta4.1.1.1/", str);
	}
	
	@Test
	public void testNotExistFolderError(){
		SgadController controller = new SgadController();
		Exception ex = null;
		try {
			controller.mountPath("./src/test/pasta");
		} catch (PastaNaoEncontradaException | NaoEhPastaException e) {
			ex = e;
		}
		assertTrue(ex instanceof PastaNaoEncontradaException);
	}
	
	@Test
	public void testNotFolderError(){
		SgadController controller = new SgadController();
		Exception ex = null;
		try {
			controller.mountPath("./src/test/pasta0/arquivo0.txt");
		} catch (PastaNaoEncontradaException | NaoEhPastaException e) {
			ex = e;
		}
		assertTrue(ex instanceof NaoEhPastaException);
	}
	
	@Test
	public void testFileSeach() throws PastaNaoEncontradaException, NaoEhPastaException, ArquivoNaoEncontradoException{
		SgadController controller = new SgadController();
		controller.mountPath("./src/test/pasta0");
		Iterator<Object> i = controller.seachByName("arquivo2", 3);
		String str = "";
		while(i.hasNext()){
			str = str + i.next();
		}
		assertEquals("/pasta0/pasta2/pasta2.1/arquivo2.mp3/pasta0/pasta2/pasta2.1/arquivo2.txt", str);
	}
	
	@Test
	public void testFolderSeach() throws PastaNaoEncontradaException, NaoEhPastaException, ArquivoNaoEncontradoException{
		SgadController controller = new SgadController();
		controller.mountPath("./src/test/pasta0");
		Iterator<Object> i = controller.seachByFolder("pasta4.1.1", 3);
		String str = "";
		while(i.hasNext()){
			str = str + i.next();
		}
		assertEquals("/pasta0/pasta4/pasta4.1/pasta4.1.1/", str);
	}
	
	@Test
	public void testExtensionSeach() throws PastaNaoEncontradaException, NaoEhPastaException, ArquivoNaoEncontradoException, TipoNaoEncontradoException{
		SgadController controller = new SgadController();
		controller.mountPath("./src/test/pasta0");
		Iterator<Object> i = controller.seachByType("mp3", 0);
		String str = "";
		while(i.hasNext()){
			str = str + i.next();
		}
		assertEquals("/pasta0/pasta1/arquivo1.mp3/pasta0/pasta2/pasta2.1/arquivo2.mp3", str);
	}

}
