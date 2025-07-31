

# Quarkus e Datadog - Observabilidade Máxima

## Introdução à Observabilidade

### Definição e importância da observabilidade em ambientes modernos

### Três pilares da observabilidade: Logs, Métricas e Traces

### Desafios comuns em ambientes distribuídos e microsserviços

## Por que Quarkus?

### Startup time extremamente rápido (ideal para escala e testes rápidos)

### Baixo consumo de memória → ideal para ambientes serverless e containers

### Suporte nativo ao Micrometer e OpenTelemetry

### Facilidade de configuração com application.properties

### Extensões prontos para Prometheus, Jaeger, Zipkin, e Datadog

### Exposição automática de métricas via /q/metrics (MicroProfile Metrics)

### Geração automática de spans e propagação de contexto com OpenTelemetry

### Integração simplificada com ferramentas de tracing e logging

### Produtividade para desenvolvedores: hot reload e dev UI para debug

## Por que Datadog para Observabilidade?

### Plataforma unificada: logs, métricas, traces, dashboards, alertas

### Auto discovery de serviços e dependências com APM

### Mapas de serviços e análise de dependência entre microsserviços

### Correlation entre logs, métricas e traces em um clique

### Agentes leves e SDKs prontos para várias linguagens (incluindo Java)

### Integração nativa com Kubernetes, Docker, AWS, GCP, Azure

### Dashboards customizáveis e compartilháveis com times

### Análise de performance de endpoints e identificação de gargalos

### SLOs, SLIs e Error Budgets diretamente integráveis no monitoramento

### Detecção de anomalias com Machine Learning

## Integração Quarkus + Datadog
### Exportação de métricas com Micrometer para Datadog

### Uso de labels e tags para segmentação por serviço, versão e ambiente

### Enriquecimento de logs com MDC e contexto distribuído

### Logging estruturado com JSON + Datadog Agent

### Observabilidade ponta a ponta com zero instrumentation adicional

## Boas práticas e casos de uso
### Definição de SLOs com base em dados reais do Datadog

### Alertas com base em métricas customizadas do Quarkus

### Uso de dashboards para squads específicos (ex: latência por equipe)

### Logging orientado a contexto: trace ID, span ID, user ID

### Análise de regressão de performance com Datadog durante o CI/CD

### Debug em produção com traces detalhados vindos do Quarkus

# Conclusão
### Ganhos de produtividade e confiabilidade usando Quarkus com Datadog

### Observabilidade como facilitadora da cultura DevOps e SRE

### Redução do MTTR (Mean Time to Resolution) com visibilidade integrada

### Reforço: “Não dá pra gerenciar o que não se pode observar”
    