package com.example.armsapp.data.local

import com.example.armsapp.R
import com.example.armsapp.domain.model.ArmsTeam
import com.example.armsapp.domain.model.ArmsWeAre
import com.example.armsapp.domain.model.EndPoints
import com.example.armsapp.domain.model.Project

val listProjects =
    listOf(
        Project(
            id = 0,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/09/391734590_18407461759059204_7470893957857138175_n.jpg",
            linkPage = "https://www.behance.net/gallery/106458633/ARARUNA-Logo-design-branding-lettering",
            name = "ARARUNA",
            type = "MOBILIÁRIO",
        ),
        Project(
            id = 1,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/fast_capa-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/187984833/FAST-FREIOS-Brand-identity-logo-design",
            name = "FAST FREIOS",
            type = "FRENAGEM",
        ),
        Project(
            id = 2,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/lanevo_capa-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/124821401/LAN-EVO-brand-Identity-logo-design-visual-identity",
            name = "LANEVO",
            type = "TECNOLOGIA",
        ),
        Project(
            id = 3,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/10/Rectangle-57-1.png",
            linkPage = "https://www.behance.net/gallery/107833365/CELINA-LOCKS-BEAUTY-Brand-design-logotype-icon",
            name = "CELINA LOCKS BEAUTY",
            type = "PERSONAL BRANDING",
        ),
        Project(
            id = 4,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/cuepas_capa-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/106595439/CUEPAS-ACAI-Brand-Identity-logo-design",
            name = "CUEPAS AÇAI",
            type = "GELADOS COMESTIVEIS",
        ),
        Project(
            id = 5,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/12/pira_1922-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/209341527/PIRACICABA-1922-Visual-identity-Design",
            name = "PIRACICABA 1922",
            type = "VISUAL IDENTITY",
        ),
        Project(
            id = 6,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/oescafandro_capa-1-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/176255729/O-ESCAFANDRO-Brand-Identity",
            name = "ESCAFANDRO",
        ),
        Project(
            id = 7,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/actplan_capa-1536x864.jpg",
            linkPage = "https://www.behance.net/gallery/190146771/ACTPLAN-Brand-Design-Visual-identity-Logo-Design",
            name = "ACTPLAN",
            type = "CONSTRUÇÃO",
        ),
        Project(
            id = 8,
            urlImage = "https://arms.com.br/wp-content/uploads/2024/11/IMG_0825-1536x1024.jpg",
            linkPage = "https://www.behance.net/gallery/214315639/NHO-QUIM-CERVEJARIA-Photography",
            name = "NHÔ QUIN",
            type = "CERVEJARIA",
        ),
    )

val listArmsWeAre =
    listOf(
        ArmsWeAre(R.string.arms_we_are_title_diagnostic, R.string.arms_we_are_description_diagnostic),
        ArmsWeAre(R.string.arms_we_are_title_naming, R.string.arms_we_are_description_naming),
        ArmsWeAre(R.string.arms_we_are_title_logo, R.string.arms_we_are_description_logo),
        ArmsWeAre(R.string.arms_we_are_title_visual, R.string.arms_we_are_description_visual),
        ArmsWeAre(
            R.string.arms_we_are_title_communication,
            R.string.arms_we_are_description_communication,
        ),
        ArmsWeAre(R.string.arms_we_are_title_mkt, R.string.arms_we_are_description_mkt),
        ArmsWeAre(R.string.arms_we_are_title_photos, R.string.arms_we_are_description_photos),
    )

val listArmsTeam =
    listOf(
        ArmsTeam(
            id = 0,
            name = "ROGÉRIO FORTI",
            jobPosition = "DIRETOR DE CRIAÇÃO",
            instagramLabel = "@studioarms",
            instagramUrl = EndPoints.INSTAGRAM,
            imageUrl = EndPoints.TEAM_ROGERIO,
        ),
        ArmsTeam(
            id = 1,
            name = "JEAN NOVAES",
            jobPosition = "DIRETOR AUDIOVISUAL",
            instagramLabel = "@jeanovaes",
            instagramUrl = EndPoints.INSTAGRAM_BASE + "/jeanovaes/",
            imageUrl = EndPoints.TEAM_JEAN,
        ),
        ArmsTeam(
            id = 2,
            name = "ANDREA SIMÃO",
            jobPosition = "ASSESSORA CONTÁBIL",
            instagramLabel = "@andreac_simao",
            instagramUrl = EndPoints.INSTAGRAM_BASE + "/andreac_simao/",
            imageUrl = EndPoints.TEAM_ANDREA,
        ),
        ArmsTeam(
            id = 3,
            name = "MAYARA FIGUEIREDO",
            jobPosition = "ADVOGADA ESPECIALISTA EM REGISTRO DE MARCAS",
            instagramLabel = "@mayarafigueiredo.adv",
            instagramUrl = EndPoints.INSTAGRAM_BASE + "/mayarafigueiredo.adv/",
            imageUrl = EndPoints.TEAM_MAYARA,
        ),
        ArmsTeam(
            id = 4,
            name = "SÍDNEY BRETANHA",
            jobPosition = "REDATOR, LICENCIADO EM LETRAS, ESCRITOR, DRAMATURGO E ATOR",
            instagramLabel = "@sbretanha",
            instagramUrl = EndPoints.INSTAGRAM_BASE + "/sbretanha/",
            imageUrl = EndPoints.TEAM_SIDNEY,
        ),
    )
