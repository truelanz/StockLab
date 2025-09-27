## StockLab - Sistema de gerenciamento laboratorial

### Back-End:
- Java Spring Boot
- PostGreSQL com SupaBase
- Jakarta validation
- ORM JPA
- H2 Database

---
### Objetivos iniciais:
- Gerenciamento básico de estoque
- Gerenciamento básico de clientes

---
### Configuração Supabase:
1. Criar conta no supabase
2. Cria um database postgreSQL
3. Ir em "Connection" na barra superior
4. Em "Type" seleciona `JDBC`
5. Em "Session pooler", copiar.
6. Separar a URI assim:

    ```
    jdbc:postgresql://aws-5-us-east-2.pooler.supabase.com:5432/postgres
    user=postgres.aqwsderfgtyhjuikolpm
    password=[YOUR-PASSWORD]
    ```
7. Adcionar a senha que você colocou ao criar o DB como variável de ambiente na IDE ou sistema, de acordo com os nomes que estão em `resources/application-dev.properties`
8. Colocar como valor dessas envs as conecções do supabase.
9. Em `application.properties`, alterar entre `test` se quiser fazer testes com H2, ou Alterar DB no Supabase `dev`

