package br.ufjf.avaliacao.persistent.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;

import br.ufjf.avaliacao.model.Curso;
import br.ufjf.avaliacao.model.Pergunta;
import br.ufjf.avaliacao.model.Questionario;
import br.ufjf.avaliacao.model.Usuario;
import br.ufjf.avaliacao.persistent.GenericoDAO;
import br.ufjf.avaliacao.persistent.IQuestionarioDAO;

public class QuestionarioDAO extends GenericoDAO implements IQuestionarioDAO {

	@SuppressWarnings("unchecked")
	public List<Questionario> retornaQuestinariosCurso(Curso curso) {
		try {
			Query query = getSession().createQuery(
					"SELECT q FROM Questionario AS q WHERE q.curso = :curso");
			query.setParameter("curso", curso);

			List<Questionario> questionarios = query.list();

			getSession().close();

			if (questionarios != null)
				return questionarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Questionario> retornaQuestinariosCursoTipo(Curso curso,
			Integer tipoQuestionario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q WHERE q.curso = :curso AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", curso);
			query.setParameter("tipoQuestionario", tipoQuestionario);

			List<Questionario> questionarios = query.list();

			getSession().close();

			if (questionarios != null)
				return questionarios;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Questionario retornaQuestinarioAtivo(Curso curso,
			Integer tipoQuestionario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q WHERE q.curso = :curso AND q.tipoQuestionario = :tipoQuestionario AND q.ativo = :ativo");
			query.setParameter("curso", curso);
			query.setParameter("tipoQuestionario", tipoQuestionario);
			query.setParameter("ativo", true);

			Questionario questionario = (Questionario) query.uniqueResult();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Questionario> retornaQuestinariosParaUsuario(Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario as q WHERE q.curso = :curso AND q.ativo = :ativo");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);

			List<Questionario> questionario = query.list();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Questionario retornaQuestinarioParaUsuarioCoord(Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q LEFT JOIN FETCH q.prazos WHERE q.curso = :curso AND q.ativo = :ativo AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);
			query.setParameter("tipoQuestionario", Questionario.COORD);

			Questionario questionario = (Questionario) query.uniqueResult();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Questionario retornaQuestinarioParaUsuarioAutoAvaliacao(
			Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q LEFT JOIN FETCH q.prazos WHERE q.curso = :curso AND q.ativo = :ativo AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);
			query.setParameter("tipoQuestionario", Questionario.AUTO);

			Questionario questionario = (Questionario) query.uniqueResult();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Questionario retornaQuestinarioParaUsuarioInfra(Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q LEFT JOIN FETCH q.prazos WHERE q.curso = :curso AND q.ativo = :ativo AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);
			query.setParameter("tipoQuestionario", Questionario.INFRA);

			Questionario questionario = (Questionario) query.uniqueResult();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public Questionario retornaQuestionarioProf(Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q WHERE q.curso = :curso AND q.ativo = :ativo AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);
			query.setParameter("tipoQuestionario", 1);
			
			Questionario q = (Questionario) query.uniqueResult();
			
			getSession().close();
			
			if(q!=null) {
				return q;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


	public Questionario retornaQuestionarioPergunta(Pergunta pergunta) { // retorna o questionario de uma pergunta
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario AS q LEFT JOIN FETCH q.perguntas as ps WHERE ps =:pergunta");
			query.setParameter("pergunta", pergunta);
		
			Questionario questionario = (Questionario) query.uniqueResult();
			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Questionario> retornaQuestionariosSemestre(String semestre){//retornas os questionarios de um semestre
		PerguntaDAO  perguntaDAO = new PerguntaDAO();
		List<Pergunta> perguntas = perguntaDAO.retornaPerguntasSemestre(semestre);
		List<Questionario> questionarios = new ArrayList<Questionario>();
		for(int i=0;i<perguntas.size();i++){
			if(!questionarios.contains(retornaQuestionarioPergunta(perguntas.get(i))))
				questionarios.add(retornaQuestionarioPergunta(perguntas.get(i)));
		}
		if(perguntas.size()>0)
			return questionarios;
		return null;
	}
}