package br.com.arquitetura.guilherme.contoller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.arquitetura.guilherme.modelos.Funcionario;

@RestController
public class FuncionarioController {

	private List<Funcionario> funcionarios = new ArrayList<>();

	@RequestMapping(value = "/funcionario", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Funcionario funcionario) {

		for (Funcionario f : funcionarios) {
			if (f.getId().equals(funcionario.getId())) {
				return ResponseEntity.badRequest().body("Funcionario com esse ID já existe");
			}
		}

		funcionarios.add(funcionario);
		return ResponseEntity.ok(funcionario);

	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.GET)
	public Funcionario buscaFuncionario(@PathVariable("id") Long id) {
		for (Funcionario f : funcionarios) {
			if (f.getId().equals(id)) {
				return f;
			}
		}
		return null;
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.PUT)
	public Funcionario atualizaFuncionario(@PathVariable("id") Long id, @RequestBody Funcionario funcionario) {

		for (Funcionario f : funcionarios) {
			if (f.getId().equals(id)) {
				f.setIdade(funcionario.getIdade());
				f.setNome(funcionario.getNome());
				f.setSalario(funcionario.getSalario());
			}
		}

		return funcionario;
	}

	@RequestMapping(value = "/funcionario", method = RequestMethod.GET)
	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	@RequestMapping(value = "/funcionario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deletaFuncionario(@PathVariable("id") Long id) {
		for (Funcionario funcionario : funcionarios) {
			if (funcionario.getId().equals(id)) {
				funcionarios.remove(funcionario);
				return ResponseEntity.ok(id);
			}
		}
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Não encontrado");
	}
}
