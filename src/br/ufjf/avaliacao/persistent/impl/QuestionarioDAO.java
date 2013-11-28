package br.ufjf.avaliacao.persistent.impl;

import java.util.List;

import org.hibernate.Query;

import br.ufjf.avaliacao.model.Curso;
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
	
	@SuppressWarnings("unchecked")
	public List<Questionario> retornaQuestinariosParaUsuarioCoord(Usuario usuario) {
		try {
			Query query = getSession()
					.createQuery(
							"SELECT q FROM Questionario as q WHERE q.curso = :curso AND q.ativo = :ativo AND q.tipoQuestionario = :tipoQuestionario");
			query.setParameter("curso", usuario.getCurso());
			query.setParameter("ativo", true);
			query.setParameter("tipoQuestionario", 0);

			List<Questionario> questionario = query.list();

			getSession().close();

			if (questionario != null)
				return questionario;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}