package dbm;

import com.mycompany.xxxproyectoxxx.HeeramientaHibernate;
import java.util.List;
import modelos.Clientes;
import modelos.Conceptos;
import modelos.Facturas;
import modelos.Usuarios;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Pedro DLF
 */
public class DBManager {

    private SessionFactory sf;
    private Session s;
    private Transaction tx;
    private Query q;

    public boolean existeUsuario(Usuarios usuario) {
        boolean existe = false;
        System.out.println("Usuario metodo= " + usuario.getNickName());
        String selectQuery = "SELECT * FROM Usuarios WHERE nick_Name = :nickNameParam";
        sf = HeeramientaHibernate.getSessionFactory();
        System.out.println("Session Factory = " + sf.toString());
        try {
            s = sf.getCurrentSession();
            System.out.println("misma sesion");
        } catch (Exception e) {
            s = sf.openSession();
            System.out.println("nueva sesion " + e.getMessage());
        }
        tx = s.beginTransaction();
        System.out.println("tx = " + tx.toString());
        q = s.createSQLQuery(selectQuery);
        System.out.println("q= " + q.getQueryString());
        q.setParameter("nickNameParam", usuario.getNickName());
        System.out.println("q.getQuery = " + q.getQueryString());
        @SuppressWarnings("unchecked")
        List<Usuarios> x = q.list();
        if (x.isEmpty()) {
        } else {
            existe = true;
        }
        tx.commit();
        s.close();
        return existe;
    }

    public void nuevoUsuario(Usuarios user) {
        System.out.println("Usuario= " + user.getNickName() + " Password= " + user.getPassword());
        sf = HeeramientaHibernate.getSessionFactory();
        try {
            s = sf.getCurrentSession();
            System.out.println("misma sesion");
        } catch (Exception e) {

            s = sf.openSession();
            System.out.println("nueva sesion " + e.getMessage());
        }
        tx = s.beginTransaction();
        s.save(user);
        tx.commit();
        s.close();
        System.out.println("sesion" + s.toString());

    }

    public boolean verificarLog(Usuarios user) {
        System.out.println("Verificar Log de " + user.getNickName());
        boolean logOK = false;
        String query = "SELECT * FROM usuarios WHERE nick_name = :nickParam AND password = :passParam";
        sf = HeeramientaHibernate.getSessionFactory();
        try {
            s = sf.getCurrentSession();
            System.out.println("misma sesion");
        } catch (Exception e) {
            s = sf.openSession();
            System.out.println("nueva sesion " + e.getMessage());
        }
        tx = s.beginTransaction();
        q = s.createSQLQuery(query);
        q.setParameter("nickParam", user.getNickName());
        q.setParameter("passParam", user.getPassword());
        @SuppressWarnings("unchequed")
        List<Usuarios> x = q.list();
        tx.commit();

        if (x.isEmpty()) {
            System.out.println("Usuario o password Incorrectos");
        } else {
            logOK = true;
            System.out.println("Log ok ");
        }
        s.close();
        return logOK;
    }

    public Usuarios getUserData(Usuarios user) {
        Usuarios usuario = null;
        String query = "SELECT * FROM usuarios WHERE nick_name = :nickParam";
        sf = HeeramientaHibernate.getSessionFactory();
        try {
            s = sf.getCurrentSession();
            System.out.println("misma sesion");
        } catch (Exception e) {
            s = sf.openSession();
            System.out.println("nueva sesion " + e.getMessage());
        }
        tx = s.beginTransaction();
        q = s.createSQLQuery(query);
        q.setParameter("nickParam", user.getNickName());
        System.out.println("cargamos usuario = " + user.getNickName());
        List<Object[]> y = q.list();
        Object[] x = y.get(0);
        System.out.println("la posicion o = " + x[0]);
        try {
            usuario = new Usuarios((String) x[1], (String) x[2], (String) x[3], (String) x[4], (String) x[5], (String) x[6], (String) x[7], 0);
            usuario.setIdUsuario((Integer) x[0]);

        } catch (Exception e) {
            usuario = new Usuarios(user.getNickName(), user.getPassword());
        }
        System.out.println("y = " + y);
        System.out.println("Datos de y =" + String.valueOf(y.size()));
        System.out.println("Razon Social = " + usuario.getPassword());
        System.out.println("Id Usuario = " + usuario.getIdUsuario());
        tx.commit();

        s.close();

        return usuario;
    }

    public void setUserData(Usuarios user) {
        System.out.println("Añadiendo datos..........");
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        try {
            s.update(user);
            tx.commit();
            System.out.println("datos de " + user.getNickName() + " Añadidos");
        } catch (Exception e) {
            System.out.println("Los datos no se han guardado correctamente Error= " + e.getMessage());
        } finally {
            s.close();
        }

    }

    public String cargarnumerofactura(Usuarios user) {
        System.out.println("Cargando numero de factura.....");
        String x = "";
        String query = "select max(numeroFactura) from Facturas where nick_name = " + user.getIdUsuario();
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        q = s.createQuery(query);
        try {
            int n =(Integer) q.uniqueResult();
            System.out.println("n= "+n);
            //List<Facturas> lista = q.list();
            //x = String.valueOf(lista.get(0).getNumeroFactura() + 1);
            x = String.valueOf(n+1);
        } catch (Exception e) {
            x = "1";
            System.out.println("Sin facturtas creadas " + e.getMessage());
        }

        
        s.close();
        System.out.println("El numero de factura actuas es " + x);
        return x;
    }

    public List<Clientes> recuperarClientes(Usuarios user) {

        System.out.println("Recuperando clientes......");
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        System.out.println("user = " + user.getNickName());
        q = s.createQuery("from Clientes c where c.nickName=  " + user.getIdUsuario() + " order by c.razonSocial");
        System.out.println("De " + user.getNickName());
        @SuppressWarnings("unchecked")
        List<Clientes> x = q.list();
        if (x.isEmpty()) {
            System.out.println("Sin clientes creados");
            x = null;
        } else {
            System.out.println("El numero de clientes de " + user.getNickName() + " es " + x.size() + " el Primer cliente es " + x.get(0).getRazonSocial());
        }
        tx.commit();
        s.close();
        return x;
    }

    public void guardarCliente(Clientes cliente) {
        System.out.println("Guardando Cliente...");
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        System.out.println("Cliente " + cliente.getRazonSocial());
        s.save(cliente);
        tx.commit();
        s.close();
        System.out.println("Cliente guardado");
    }

    public Usuarios recuperarUserPorId(String id) {
        Usuarios x = null;
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        String query = "from Usuarios users where users.idUsuario= " + id;
        tx = s.beginTransaction();
        q = s.createQuery(query);
        List<Usuarios> lista = q.list();
        try {
            x = lista.get(0);
            System.out.println("Usario = " + x.getNickName() + " cargado correctamente");
        } catch (Exception e) {
            System.out.println("Imposible cargar el usuario.. ");
        }
        s.close();
        return x;
    }

    public List<Conceptos> recuperarConceptos(Usuarios user) {
        System.out.println("Cargando conceptos de " + user.getNickName());
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        String query = "from Conceptos c where c.nickName =" + user.getIdUsuario() + " group by c.nombreConcepto order by c.nombreConcepto  ";
        q = s.createQuery(query);
        List<Conceptos> lista = q.list();
        try {
            System.out.println("Conceptos de " + user.getNickName() + " cargados correctamente."
                    + "totald de conceptos cargados " + lista.size() + " Primer concepto de la lista = " + lista.get(0).getNombreConcepto());
        } catch (Exception e) {
            System.out.println("Sin datos en la lista o error= " + e.getMessage());
        }
        s.close();
        return lista;
    }

    public void guardarConceptos(Conceptos concepto) {
        System.out.println("Guardando Concepto.......");
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        try {
            s.save(concepto);
            tx.commit();
            System.out.println("concepto guardado");
        } catch (Exception e) {
            System.out.println("Error al guardar los datos del concepto ERROR = " + e.getMessage());
        }

        s.close();
    }

    public Conceptos recuperarConceptoPorID(String id) {
        Conceptos concepto = null;
        System.out.println("Recuperando Concepto con id =" + id);
        sf = HeeramientaHibernate.getSessionFactory();
        s = sf.openSession();
        tx = s.beginTransaction();
        String query = "from Conceptos c where c.idConcepto = " + id;
        try {
            q = s.createQuery(query);
            List<Conceptos> lista = q.list();
            concepto = lista.get(0);
            System.out.println("El cocepto " + concepto.getNombreConcepto() + " se ha cargado correctamente");
        } catch (Exception e) {
            System.out.println("Erro al cargar el concepto causa ERROR = " + e.getMessage());
        }
        s.close();
        return concepto;
    }

    public List<Conceptos> recuperarconceptosdeFactura(String numeroFactura, int idUsuario) {
        System.out.println("Recuperando conceptos de Factura....");
        String query = "from Conceptos c where c.numeroFactura =" + numeroFactura + " and c.nickName = " + idUsuario;
        List<Conceptos> lista = null;
        try {
            sf = HeeramientaHibernate.getSessionFactory();
            s = sf.openSession();
            tx = s.beginTransaction();
            q = s.createQuery(query);
            lista = q.list();

            System.out.println("Conceptos de factura recuperados " + lista.size());
        } catch (Exception e) {
            System.out.println("ERROR Conceptos de factura no recuperados e= " + e.getMessage());
        }
        s.close();
        return lista;
    }

    public Clientes recuperarclienteporID(String idCliente) {
        Clientes c = null;
        String query = "from Clientes c  where c.idCliente = " + idCliente;
        try {
            sf = HeeramientaHibernate.getSessionFactory();
            s = sf.openSession();
            tx = s.beginTransaction();
            q = s.createQuery(query);
            List<Clientes> lista = q.list();
            c = lista.get(0);
            s.close();
            System.out.println("Cliente cargado con exito");
        } catch (Exception e) {
            System.out.println("error al recuperar cliente causa = " + e.getMessage());
        }

        return c;
    }
    
    public void guardarFactura(Facturas Factura){
        System.out.println("Guardando Factura");
        try {
            sf = HeeramientaHibernate.getSessionFactory();
            s = sf.openSession();
            tx = s.beginTransaction();
            s.save(Factura);
            tx.commit();
            s.close();
            System.out.println("Factura Guardada");
        } catch (Exception e) {
            System.out.println("Error al guardar factutra causa = "+e.getMessage());
        }
    
    
    }

}
